<%@page import="kr.bit.model.MemberVO"%>
<%@page import="kr.bit.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num")); 
	
	MemberDAO dao = new MemberDAO(); 
	MemberVO vo = dao.memberContent(num); 
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css'>
<script src='https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js'></script>
</head>
<body>
<form action="memberUpdate.jsp" method="post">
<input type="hidden" name="num" value="<%=vo.getNum()%>"/>
<table class="table table-bordered">
<% if(vo != null) { %>
  <tr>
    <td colspan="2"><%=vo.getName() %>회원 상세보기</td>
  </tr>
  <tr>
    <td>번호</td>
    <td><%=vo.getNum() %></td>
  </tr>
  <tr>
    <td>아이디</td>
    <td><%=vo.getId() %></td>
  </tr>
  <tr>
    <td>비밀번호</td>
    <td><%=vo.getPass() %></td>
  </tr>
  <tr>
    <td>이름</td>
    <td><%=vo.getName() %></td>
  </tr>
  <tr>
    <td>비밀번호</td>
    <td><%=vo.getPass() %></td>
  </tr>
  <tr>
    <td>나이</td>
    <td><input type="text" name="age" value="<%=vo.getAge() %>"/></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="text" name="email" value="<%=vo.getEmail() %>"/></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone" value="<%=vo.getPhone() %>"/></td>
  </tr>
<% } else { %>
  <tr>
    <td>일치하는 회원이 없습니다.</td>
  </tr>
<% } %>
  <tr>
    <td colspan="2" align="center">
    	<input type="submit" value="수정하기" class="btn btn-primary"/>
    	<input type="reset" value="취소" class="btn btn-warning"/>
    	<input type="button" value="리스트" onclick="location.href='memberList.jsp'" class="btn"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>