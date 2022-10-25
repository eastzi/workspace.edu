package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberUpdate.do")
public class MemberUpdateController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//1. 클라이언트의 요청을 받아서 파라메터를 수집하기(vo)! - num, age, email, phone 4개의 파라메터
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
	
		if(cnt > 0) {
			/*수정성공
			out.println("insert success");
			수정성공시 회원리스트 보기로 이동 -> memberList.do로 이동*/
			response.sendRedirect("/MVC03/memberList.do") ;
		}else {
			//가입실패 -> 예외객체를 만들어서 was에 던지기 
			throw new ServletException("not update"); 
		}
		
	}

}
