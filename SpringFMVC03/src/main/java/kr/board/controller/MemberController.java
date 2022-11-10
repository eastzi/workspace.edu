package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper memberMapper;

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
			m.getMemAge() == 0 ||
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
		int result = memberMapper.register(m);
		if(result == 1) { // 1 = 회원가입성공
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
			// 회원가입 성공시 로그인처리하기
			session.setAttribute("mvo", m); // js에서 회원가입 여부 체크 -> empty이면 회원가입안함.
			return "redirect:/"; //index.jsp
		}else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "이미 존재하는 회원입니다.");
			return "redirect:/memJoin.do";
		}
	}
	
	//로그아웃 처리
	@RequestMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	
	//로그인 화면으로 이동
	@RequestMapping("/memLoginForm.do")
	public String memLogoutForm() {
		return "member/memLoginForm"; //memLoginForm.jsp
	}
	
	// 로그인 기능 구현
	@RequestMapping("/memLogin.do")
	public String memLogin(Member m, RedirectAttributes rttr, HttpSession session) {
		if(m.getMemID() == null || m.getMemID().equals("") || 
		   m.getMemPassword() == null || m.getMemPassword().equals("")) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력해주세요");
			return "redirect:/memLoginForm.do";
		}
		Member mvo = memberMapper.memLogin(m);
		if(mvo != null) {
			//로그인 성공
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "로그인에 성공했습니다.");
			session.setAttribute("mvo", mvo); 
			return "redirect:/";
		}else {
			// 로그인 실패
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "다시 로그인 해주세요.");
			return "redirect:/memLoginForm.do";
		}
		
	}
}
