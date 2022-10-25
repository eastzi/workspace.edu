<%@page import="kr.bit.model.MemberVO"%>
<%@page import="kr.bit.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	//1. 클라이언트의 요청을 받아서 파라메터를 수집하기(vo)! - num, age, email, phone 4개의 파라메터
	int num = Integer.parseInt(request.getParameter("num")); 
	int age = Integer.parseInt(request.getParameter("age")); 
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");
	
	MemberVO vo = new MemberVO(); 
	vo.setNum(num);
	vo.setAge(age);
	vo.setEmail(email);
	vo.setPhone(phone);
	System.out.println(vo.toString());
	
	//dao 모델 객체를 연결해서 업데이트하기 -> dao에 업데이트 함수 만들기 
	MemberDAO dao = new MemberDAO(); 
	int cnt = dao.memberUpdate(vo);
	
	if(cnt > 0) {
		/*수정성공
		out.println("insert success");
		수정성공시 회원리스트 보기로 이동 -> memberList.jsp로 이동*/
		response.sendRedirect("memberList.jsp") ;
	}else {
		//가입실패 -> 예외객체를 만들어서 was에 던지기 
		throw new ServletException("not update"); 
	}
%>    