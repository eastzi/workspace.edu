package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberUpdateController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
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
