package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberContent.do")
public class MemberContentController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/MVC01/memberContent.do?num=3
		int num = Integer.parseInt(request.getParameter("num")); 
		
		MemberDAO dao = new MemberDAO(); 
		MemberVO vo = dao.memberContent(num); 
		
		//3. jsp와 forward 하기 (c와 v 연결하기)
		//객체바인딩(request 바인딩)
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("member/memberContent.jsp");
		rd.forward(request, response); //------------------------------▲ controlle가 가진 request와 response객체를 jsp로 넘김
        //                                                              (jsp에서 해당 객체에 저장된 정보를 빼낼수 있음 -> getAttribute("대표이름"))
	}

}
