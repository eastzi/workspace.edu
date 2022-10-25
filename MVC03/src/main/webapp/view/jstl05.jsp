<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//controller 역할 대행하기(편의를 위해)
	String[] str = {"사과", "바나나", "포도", "귤", "오렌지"}; 
	request.setAttribute("str", str);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ${식} -> 식에는 변수나 속성이 들어감 -->
<c:forEach var="f" items="${str}"> <!-- el로 속성을 받은 경우 getAttribute를 할 필요가 없음. -->
	${f}<br> <!-- 배열의 데이터를 f로 받아서 출력 -->
</c:forEach>
</body>
</html>