<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Date d = new Date(); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  wellcom eastzi homepage!!!(jsp->동적인페이지)<br>
  지금 몇시지? <%=d.toString() %> (시간 변경가능)
</body>
</html>