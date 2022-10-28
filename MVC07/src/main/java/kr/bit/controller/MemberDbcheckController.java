package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDbcheckController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 아이디 중복체크
		// memberRegister.jsp의 ajax에서 넘어오는 data인 id를 받음
		String id = request.getParameter("id");
		//DB에 동일한 아이디가 있는지 체크해야 하므로 DAO객체 생성 -> DAO에 중복체크 메소드 생성
		MemberDAO dao = new MemberDAO(); 
		String dbDouble = dao.memberDbcheck(id);
		
		// memberRegister.jsp의 ajax의 success callback(dbCheck)함수로 응답됨.
		response.getWriter().print(dbDouble);
		
		return null; //다른페이지로 화면전환 안하기 위한 null
	}

}
