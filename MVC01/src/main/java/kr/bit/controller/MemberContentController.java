package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css'>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js'>");
		out.println("</script>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js'>");
		out.println("</script>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js'>");
		out.println("</script>");
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<form action='/MVC01/memberUpdate.do' method='post'>");
		//hidden -> 수정대상은 아니지만 파라메터 값으로 넘기기 위함. 수정하기위한 회원을 지정하는 파라메터 = num로 결정함! 
		out.println("<input type='hidden' name='num' value='"+vo.getNum()+"'/>");
		out.println("<table class='table table-bordered'>");
		if(vo != null) {
			out.println("<tr>");
			out.println("<td colspan='2'>"+vo.getName()+" 회원의 상세보기</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>번호</td>");
			out.println("<td>"+vo.getNum()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>아이디</td>");
			out.println("<td>"+vo.getId()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>비밀번호</td>");
			out.println("<td>"+vo.getPass()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>이름</td>");
			out.println("<td>"+vo.getName()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>나이</td>");
			out.println("<td><input type='text' name='age' value='"+vo.getAge()+"'/></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>이메일</td>");
			out.println("<td><input type='text' name='email' value='"+vo.getEmail()+"'/></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>전화번호</td>");
			out.println("<td><input type='text' name='phone' value='"+vo.getPhone()+"'/></td>");
			out.println("</tr>");
		} else {
			out.println("<tr>");
			out.println("<td>일치하는 회원이 없습니다.</td>");
			out.println("</tr>");
		}
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println("<input type='submit' value='수정하기' class='btn btn-primary'/>");
		out.println("<input type='reset' value='취소' class='btn btn-warning'/>");
		out.println("<a href='/MVC01/memberList.do' class='btn btn-primary'>리스트</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("</body>"); 
		out.println("</html>");
	}

}
