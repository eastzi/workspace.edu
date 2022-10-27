package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDeleteController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
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

}
