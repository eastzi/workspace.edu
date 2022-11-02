<%@page import="kr.bit.model.MemberVO"%>
<%@page import="kr.bit.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/> <!-- MVC04 -->
<%
	//MemberVO vo = (MemberVO)request.getAttribute("vo");
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
<form action="${ctx}/memberUpdate.do" method="post">
<input type="hidden" name="num" value="${vo.num }"/>
<table class="table table-bordered">
<c:if test="${vo != null}">
  <tr>
    <td colspan="2">${vo.name}회원 상세보기</td>
  </tr>
  <tr>
    <td>번호</td>
    <td>${vo.num }</td>
  </tr>
  <tr>
    <td>아이디</td>
    <td>${vo.id}</td>
  </tr>
  <tr>
    <td>비밀번호</td>
    <td>${vo.pass}</td>
  </tr>
  <tr>
    <td>이름</td>
    <td>${vo.name}</td>
  </tr>
  <tr>
    <td>나이</td>
    <td><input type="text" name="age" value="${vo.age}"/></td>
  </tr>
  <tr>
    <td>이메일</td>
    <td><input type="text" name="email" value="${vo.email}"/></td>
  </tr>
  <tr>
    <td>전화번호</td>
    <td><input type="text" name="phone" value="${vo.phone}"/></td>
  </tr>
</c:if>
  <tr>
    <td colspan="2" align="center">
    	<input type="submit" value="수정하기" class="btn btn-primary"/>
    	<input type="reset" value="취소" class="btn btn-warning"/>
    	<input type="button" value="리스트" onclick="location.href='${ctx}/memberList.do'" class="btn"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>