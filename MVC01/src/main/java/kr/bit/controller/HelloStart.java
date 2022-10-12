package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/h.do")
public class HelloStart extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*controller의 역할
		 * 1. 클라이언트의 요청을 받는 작업(파라메터를 수집함) -> controller를 servlet의 형태로 만듬
		 * 2. 클라이언트의 요청을 처리하는 작업(비지니스로직) -> model로 별도의 class를 만들어 분리할 것
		 */
		
		int sum = 0; 
		for(int i = 0;  i < 101; i++) {
			sum += i; 
		}
		
		/*
		 * 3. 클라이언트의 요청을 처리한 후 응답하는 작업(프리젠테이션로직 -> view형태로 jsp로 처리) 
		 */
		PrintWriter out = response.getWriter(); 
		out.println("<html>");
		out.println("<body>");
		out.println(sum);
		out.println("</body>");
		out.println("</html>");
	}

}
