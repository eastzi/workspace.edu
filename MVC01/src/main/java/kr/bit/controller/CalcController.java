package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;

//calc.do라는 요청이 올 때 그 요청을 처리하는 servlet -> controller의 역할
@WebServlet("/calc.do")
public class CalcController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*servlet(controller)
		1. 클라이언트에서 넘어오는 폼 파라메터를 받기(파라메터수집(su1, su2)) 
		-> 파라메터는 request 객체에 담겨서 넘어온다. 
	
		2. request에 담겨 넘어온 데이터를 꺼내는 역할 
		getPrameter의 리턴타입은 무조건 String이므로 int로 받기위해선 형변환이 필요
		String su1 = request.getParameter("su1");
		String su2 = request.getParameter("su2");
		*/
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		//su1 ~ su2 = ?
		//비지니스로직을 model로 분리하기 
		/*
		int sum = 0;
		for(int i = su1; i <= su2; i++) {
			sum += i; 
		}
		*/
		
		MyCalc my = new MyCalc(); 
		int sum = my.hap(su1, su2); 
		
		//응답하는 부분(프리젠테이션로직 = view = jsp) 
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>TOTAL</td>");
		out.println("<td>");
		out.println(sum);
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}