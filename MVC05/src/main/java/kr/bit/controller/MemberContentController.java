package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberContentController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
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

}
