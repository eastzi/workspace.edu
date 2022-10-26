package kr.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
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
}
