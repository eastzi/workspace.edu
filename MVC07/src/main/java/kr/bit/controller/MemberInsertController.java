package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
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
		
		//파일이 첨부된 경우
		if(request.getParameter("mode").equals("fadd")) {
			String filename = request.getParameter("filename");
			vo.setFilename(filename);
		}
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
		int cnt = -1;
		
		if(request.getParameter("mode").equals("fadd")) {
			cnt = dao.memberInsertFile(vo);  //파일이름을 저장해야하는 경우
		}else {
			cnt = dao.memberInsert(vo);	//파일이름을 저장할 필요가 없는 경우		
		}
		
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
}
