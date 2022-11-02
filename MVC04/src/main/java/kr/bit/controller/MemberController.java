package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

// 6개의 POJO가 해야할 일들을 1개의 POJO로 통일
public class MemberController { //MVC에서는 controller를 implements 했으나, 

	//MemberContentController
	@RequestMapping("/memberContent.do")
	public String memberContent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num")); 
		
		MemberDAO dao = new MemberDAO(); 
		MemberVO vo = dao.memberContent(num); 
		
		//3. jsp와 forward 하기 (c와 v 연결하기)
		//객체바인딩(request 바인딩)
		request.setAttribute("vo", vo);
		return "memberContent"; 
		// 뷰의 이름만 리턴 시키고 앞뒤 경로는 prefix, subfix로 붙이기 -> 경로가 수정될 때 용이하게 하기 위함 => viewResolver
		// "/WEB-INF/member/memberContent.jsp"; //forward기법의 리턴 주소 (웹 주소 아님) 
	}
	
	//MemberDeleteController
	@RequestMapping("/memberDelete.do")
	public String memberDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //MVC04
		
		//http://localhost:8080/MVC01/memberDelete.do?num=6 -> 삭제 요청 주소
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO(); 
		int cnt = dao.memberDelete(num);
		
		String nextPage = null;
		if(cnt > 0) {
			nextPage = "redirect:" + ctx + "/memberList.do";
		}else {
			throw new ServletException("not insert"); 
		}
		return nextPage;
	}
	
	//MemberInsertController
	@RequestMapping("/memberInsert.do")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //MVC04
		
		//1. 파마메터수집하기(vo에 넘어온 데이터 묶어주기 = 수집) 
		//1-1. 클라이언트가 데이터를 입력하여 요청하면 request가 객체로 받음. 그 데이터들을 빼내는 작업
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String name = request.getParameter("name"); 
		int age = Integer.parseInt(request.getParameter("age")); 
		String email = request.getParameter("email"); 
		String phone = request.getParameter("phone"); 
		
		//1-2. 빼내온 데이터들을 묶어주는 작업 = 수집
		/*
		MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		default 생성자를 이용하는 경우 -> set으로 저장 -> 이 방식을 이용할 것.
		매개변수가 있는 생성자를 만들수도 있고 아닐 수도 있어서 기본생성자를 이용해서 필요 변수에 set으로 데이터저장
		*/
		MemberVO vo = new MemberVO(); 
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		//2. 수집된 데이터 확인용
		System.out.println(vo.toString()); //toString 생략가능
		
		//3. 수집된 파라메터를 model과 연동하기
		MemberDAO dao = new MemberDAO(); 
		int cnt = dao.memberInsert(vo);
		
		//PrintWriter out = response.getWriter(); //view에 뿌리는 빨대
		String nextPage = null; 
		if(cnt > 0) {
			/*가입성공
			out.println("insert success");*/
			//가입성공시 회원리스트 보기로 이동 -> memberList.do로 이동
			nextPage = "redirect:" + ctx + "/memberList.do";
		}else {
			//가입실패 -> 예외객체를 만들어서 was에 던지기 
			throw new ServletException("not insert"); 
		}
		return nextPage;
	}
	
	//MemberListController
	@RequestMapping("/memberList.do")
	public String memberList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//POJO의 역할
		//1. List를 가져오는 역할을 하는 POJO(Model 연동)
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.memberList();
		
		//2. 객체바인딩
		request.setAttribute("list", list);
		
		//3. POJO는 다음페이지 정보를 frontController에게 전달(nextPage리턴) 
		return "memberList";  
		// "/WEB-INF/member/memberList.jsp"; 
	}
	
	//MemberRegisterController
	@RequestMapping("/memberRegister.do")
	public String memberRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "memberRegister"; 
		// "/WEB-INF/member/memberRegister.jsp";
	}
	
	//MemberUpdateController
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //MVC04
		
		int num = Integer.parseInt(request.getParameter("num")); 
		int age = Integer.parseInt(request.getParameter("age")); 
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO(); 
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		System.out.println(vo.toString());
		
		//dao 모델 객체를 연결해서 업데이트하기 -> dao에 업데이트 함수 만들기 
		MemberDAO dao = new MemberDAO(); 
		int cnt = dao.memberUpdate(vo);
	
		String nextPage = null; 
		if(cnt > 0) {
			/*수정성공
			out.println("insert success");
			수정성공시 회원리스트 보기로 이동 -> memberList.do로 이동*/
			nextPage = "redirect:" + ctx + "/memberList.do";
		}else {
			//가입실패 -> 예외객체를 만들어서 was에 던지기 
			throw new ServletException("not update"); 
		}
		return nextPage;
	}
}
