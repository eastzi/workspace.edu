package kr.bit.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;

@WebServlet("/fc.do")
public class ForwardController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. forward
		String name = "eastzi"; 
		int age = 22;
		String email = "222@naver.com"; 
		
		//회원정보를 묶기 -> 많은 파라메터를 한번에 넘기기
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		//forward.jsp(view page)로 data 전달하여 view page에서 출력하기
		//객체바인딩
		request.setAttribute("vo", vo);
		
		//forward
		RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp");
		rd.forward(request, response);
	}

}
