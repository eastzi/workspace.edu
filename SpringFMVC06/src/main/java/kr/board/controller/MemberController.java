package kr.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.board.entity.AuthVO;
import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;
import kr.board.security.MemberUserDetailsService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MemberUserDetailsService memberUserDetailsService;
	
	@Autowired
	private PasswordEncoder pwEncoder; //securityConfig에서 bean 등록함.

	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join"; //join.jsp
	}
	
	// 아이디 중복확인
	@RequestMapping("/memRegisterCheck.do")
	public @ResponseBody int memRegisterCheck(@RequestParam("memID") String memID) {
		Member m = memberMapper.registerCheck(memID);
		if(m != null || memID.equals("")) {
			return 0; // 1. 이미 존재하는 경우, 2. 입력하지 않은 경우 -> 사용할 수 없는 아이디
		}
		
		return 1;
	}
	
	// 회원가입 처리
	@RequestMapping("/memRegister.do")
	public String memRegister(Member m, String memPassword1, String memPassword2,
									RedirectAttributes rttr, HttpSession session) {
		// 누락 체크
		if(m.getMemID() == null || m.getMemID().equals("") || 
			memPassword1 == null || memPassword1.equals("") ||
			memPassword2 == null || memPassword2.equals("") ||
			m.getMemName() == null || m.getMemName().equals("") ||
			m.getMemAge() == 0 || m.getAuthList().size() == 0 ||
			m.getMemGender() == null || m.getMemGender().equals("") ||
			m.getMemEmail() == null || m.getMemEmail().equals("")) {
			// 누락메세지를 가지고 가기 -> jsp 경로가 아니라 객체바인딩이 불가함. -> redirect시에 한번만 값을 가져가면 됨.(RedirectAttributes 사용)
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요");
			return "redirect:/memJoin.do";	
		}
		
		//pwd 1,2 동일하지 않으면 메시지 띄우기
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
			return "redirect:/memJoin.do";	
		}
		
		m.setMemProfile(""); // 사진이미지는 없다
		
		//회원을 테이블에 저장하기
		//추가 - 비밀번호 암호화하기(API)
		String encyptPw = pwEncoder.encode(m.getMemPassword()); //비밀번호를 String 타입의 암호화코드로 생성
		m.setMemPassword(encyptPw); //암호화된 비밀번호를 회원정보 비밀번호에 저장
		
		int result = memberMapper.register(m);
		if(result == 1) { // 1 = 회원가입성공
			//추가 - 권한 테이블에 회원권한 저장하기
			List<AuthVO> list = m.getAuthList();
			for(AuthVO authVO : list) {
				if(authVO.getAuth() != null) {
					AuthVO saveVO = new AuthVO(); 
					saveVO.setMemID(m.getMemID()); // 회원아이디
					saveVO.setAuth(authVO.getAuth()); // 회원의 권한			
					memberMapper.authInsert(saveVO);
				}
			}
			
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
			/* 회원가입 성공시 로그인처리하기 --> 회원가입이 성공하면 자동로그인 대신에 로그인 페이지로 이동시키기
			//getMember()는 회원정보만 가져오는데 권한정보도 함께 가져오도록 sql문 수정필요!
			Member mvo = memberMapper.getMember(m.getMemID());
			System.out.println(mvo);
			session.setAttribute("mvo", mvo); // js에서 회원가입 여부 체크 -> empty이면 회원가입안함.*/
			return "redirect:/memLoginForm.do"; 
		}else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "이미 존재하는 회원입니다.");
			return "redirect:/memJoin.do";
		}
	}
	
	/*로그아웃 처리 -> 스프링시큐리티로 처리
	@RequestMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}*/
	
	//로그인 화면으로 이동
	@RequestMapping("/memLoginForm.do")
	public String memLogoutForm() {
		return "member/memLoginForm"; //memLoginForm.jsp
	}
	
	/* 로그인 기능 구현 -> 스프링시큐리티를 사용하므로 사용하지 않는 메소드
	@RequestMapping("/memLogin.do")
	public String memLogin(Member m, RedirectAttributes rttr, HttpSession session) {
		if(m.getMemID() == null || m.getMemID().equals("") || 
		   m.getMemPassword() == null || m.getMemPassword().equals("")) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력해주세요");
			return "redirect:/memLoginForm.do";
		}
		Member mvo = memberMapper.memLogin(m);
		// 추가 - 비밀번호 일치여부 체크 
		if(mvo != null && pwEncoder.matches(m.getMemPassword(), mvo.getMemPassword())) {
			//로그인 성공
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다.");
			// 회원 이미지가 없는 session -> 이미지 등록 controller에서 회원이미지 포함 session 새롭게 생성
			session.setAttribute("mvo", mvo); 
			return "redirect:/";
		}else {
			// 로그인 실패
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "다시 로그인 해주세요.");
			return "redirect:/memLoginForm.do";
		}
		
	}
	*/
	
	//회원정보 수정화면
	@RequestMapping("/memUpdateForm.do")
	public String memUpdateForm() {
		return "member/memUpdateForm"; //memUdpateForm.jsp
	}
	
	//회원정보 수정
	@RequestMapping("/memUpdate.do")
	public String memUpdate(Member m, RedirectAttributes rttr, String memPassword1, String memPassword2, HttpSession session) {
		if(m.getMemID() == null || m.getMemID().equals("") || 
			memPassword1 == null || memPassword1.equals("") ||
			memPassword2 == null || memPassword2.equals("") ||
			m.getMemName() == null || m.getMemName().equals("") ||
			m.getMemAge() == 0 || m.getAuthList().size() == 0 ||
			m.getMemGender() == null || m.getMemGender().equals("") ||
			m.getMemEmail() == null || m.getMemEmail().equals("")) {
			// 누락메세지를 가지고 가기 -> jsp 경로가 아니라 객체바인딩이 불가함. -> redirect시에 한번만 값을 가져가면 됨.(RedirectAttributes 사용)
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요");
			return "redirect:/memUpdateForm.do";	
		}
			
		//pwd 1,2 동일하지 않으면 메시지 띄우기
		if(!memPassword1.equals(memPassword2)) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
			return "redirect:/memUpdateForm.do";	
		}
		
		//회원수정 저장하기 
		// 추가 - 비밀번호 암호화
		String encyptPw = pwEncoder.encode(m.getMemPassword());
		m.setMemPassword(encyptPw);
		
		int result = memberMapper.memUpdate(m);
		if(result == 1) { // 1 = 회원정보 수정성공
			//기존권한을 삭제하고
			memberMapper.authDelete(m.getMemID());
	
			//새로운 권한 추가하기
			List<AuthVO> list = m.getAuthList();
			for(AuthVO authVO : list) {
				if(authVO.getAuth() != null) {
					AuthVO saveVO = new AuthVO();
					saveVO.setMemID(m.getMemID());
					saveVO.setAuth(authVO.getAuth());
					memberMapper.authInsert(saveVO);
				}
			}
			
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원정보수정에 성공했습니다.");
			// 회원수정 성공시 로그인처리하기                           이미지 오류해결을 위해
			Member mvo = memberMapper.getMember(m.getMemID()); //회원수정된 데이터를 DB에서 다시 가져와서 session에 set 하기
			session.setAttribute("mvo", mvo); // js에서 회원가입 여부 체크 -> empty이면 회원가입안함.
			return "redirect:/"; //index.jsp
		}else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "회원정보수정에 실패했습니다.");
			return "redirect:/memUpdateForm.do";
		}
	}
	
	//회원 사진등록 화면
	@RequestMapping("/memImageForm.do")
	public String memImageForm() {
		return "member/memImageForm";
	}
	
	//회원사진 이미지 업로드(upload, DB저장)
	@RequestMapping("/memImageUpdate.do")
	public String memImageUpdate(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) {
		//파일업로드 API 3가지
		//1. cos.jar
		MultipartRequest multi = null; 
		int fileMaxSize = 40*1024*1024; //10MB
		String savePath = request.getRealPath("resources/upload");
		
		try {
			//request - 파라메터 가져오고
			//savePath - 이미지 저장될 장소                                           a회원이 1.png라는 이미지로 등록 후 b회원이 1.png 이미지를 
			//이미지 업로드                                                          등록하고자 할때 1_1.png로 이미지명을 자동 생성해줌.
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "파일의 크기는 100MB를 넘을 수 없습니다.");
			return "redirect:/memImageForm.do";
		}		
		
		//데이터베이스 테이블에 회원이미지 업데이트
		String memID = multi.getParameter("memID");
		String newProfile = ""; 
		File file = multi.getFile("memProfile");
		if(file != null) {
			//업로드가 된 상태(.png, .jpg, .gif)
			//이미지 파일이지 체크 -> 이미지 파일이 아니면 삭제 
			//이미지명.png 등 일때 .뒤에 있는 확장자 가져오기 
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			ext = ext.toUpperCase(); //소문자를 대문자로 변경
			if(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG")) {
				//새로 업로드된 이미지(new), 현재 DB에 있는 이미지(old)
				String oldProfile = memberMapper.getMember(memID).getMemProfile();
				File oldFile = new File(savePath + "/" + oldProfile);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				//새로운 이미지 가져오기
				newProfile = file.getName();
			}else { //확장자가 PNG, GIF, JPG가 아닌경우
				if(file.exists()) {
					file.delete(); //삭제
				}
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "이미지 파일만 업로드 가능합니다.");
				return "redirect:/memImageForm.do";
			}
		}
		
		//새로운 이미지 테이블에 업데이트
		Member mvo = new Member(); 
		mvo.setMemID(memID); 
		mvo.setMemProfile(newProfile); 
		memberMapper.memProfileUpdate(mvo); //이미지 업데이트 성공
		
		//이미지 변경 후 새로운 이미지가 담긴 session 만들기
		Member m = memberMapper.getMember(memID); //회원정보 가져오기
		//이미지가 포함된 session 생성 -> 위에선 회원 이미지가 없는 session이 있던 상태
		session.setAttribute("mvo", m);
		
		rttr.addFlashAttribute("msgType", "성공 메세지");
		rttr.addFlashAttribute("msg", "이미지 변경에 성공했습니다.");		
		return "redirect:/"; //index.jsp
	}
	
	//access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
}
