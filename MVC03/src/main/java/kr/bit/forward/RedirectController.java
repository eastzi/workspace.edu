package kr.bit.forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. forward 실습
		String data ="apple"; 
		String name = "eastzi";
		int age = 11;
		String email = "111@naver.com";
		
		//result.jsp(view page)로 data 전달하여 view page에서 출력하기
		//controller -> view page로 전환하는 방법
		/*1. redirect
		get방식으로 파라메터를 넘길 수 있지만 많은 파라메터를 넘기기에는 어려움이 있음
		redirect는 request, response객체를 끊고 jsp에서 새로 생성함 - 연동이 안됨*/
		response.sendRedirect("view/result.jsp?data="+data+"&name="+name+"&age="+age+"&email="+email);
		
	}

}
