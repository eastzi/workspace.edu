<%@page import="kr.web.util.MyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MyUtil my = new MyUtil(); 
	int sum = my.hap(); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>1~100 까지의 총합</td>
			<td><%=sum %></td>
		</tr>
	</table>
</body>
</html>