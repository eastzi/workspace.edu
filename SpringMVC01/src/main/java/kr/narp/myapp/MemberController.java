package kr.narp.myapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao; 

	@RequestMapping("/memberList.do")       // HttpServletRequest == Model
	public String memberList(Model model) { // MVC에서 객체바인딩을 위해 HttpServletRequest가 필요했음 -> Spring에서는 Model이 대신함 
		// 1. 멤버리스트를 가져오기 위해선 DB와의 연결이 필요.
		// MVC에선 DAO객체를 생성했음
		// MemberDAO dao = new MemberDAO(); 
		// ---> Spring에서는 new를 안하는 것을 목적으로 함. 컨테이너에서 만들어진 객체를 가져와야함.(DI) => @Autowired
		List<MemberVO> list = dao.memberList(); 
		
		// 객체바인딩
		model.addAttribute("list", list); 
		return "memberList"; 
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(MemberVO vo) {
		// 파라메터 수집하기(vo) -> 매개변수를 MembeVO로 받기
		
		// 한글 인코딩처리 -> web.xml에 설정(CharacterEncodingFilter)
		
		int cnt = dao.memberInsert(vo);
		
		return "redirect:/memberList.do"; 
	}
	
	@RequestMapping("/memberRegister.do")
	public String memberRegister() {
		
		
		return "memberRegister"; 
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(int num) { //매개변수명은 클라이언트에서 넘어오는 변수명과 동일하게
		                                  // 변수명을 다르게 하고싶으면 @RequestParam("num") int su 이렇게 해야함.
		//파라메터를 수집 -> num -> vo를 다 수집할 필요가 없음. 
		int cnt = dao.memberDelete(num);
		
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberContent.do")
	public String memberContent(int num, Model model) {
		//POJO의 역할
		// 1. list가져오기(Model연동)
		MemberVO vo = dao.memberContent(num); //memberContent 메소드는 회원 한명의 구조를 넘겨줌. 따라서 MemberVO로 받는것.
		
		// 2. 객체바인딩 -> model 필요
		model.addAttribute("vo", vo);
		
		// 3. nextPage리턴
		return "memberContent";
	}
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(MemberVO vo) {
		
		int cnt = dao.memberUpdate(vo);
		
		return "redirect:/memberList.do";
	}
}
