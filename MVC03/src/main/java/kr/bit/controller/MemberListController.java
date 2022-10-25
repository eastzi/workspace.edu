package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;


@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트의 요청을 받는다. -> memberList.do
		//2. 회원 전체리스트 가져오기 -> model(DAO)과 연동
		MemberDAO dao = new MemberDAO();
		
		//memberList() -> dao에서 전체 회원리스트를 가져오고 묶고 list에 담는 메서드
		ArrayList<MemberVO> list = dao.memberList(); 
		
		//3. jsp와 forward 하기 (c와 v 연결하기)
		//객체바인딩(request 바인딩)
		request.setAttribute("list", list);
		
		//forwrad 기법
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		rd.forward(request, response); //------------------------------▲ controlle가 가진 request와 response객체를 jsp로 넘김
		                               //                                (jsp에서 해당 객체에 저장된 정보를 빼낼수 있음 -> getAttribute("대표이름"))
	}
}
