<%@page import="kr.bit.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.bit.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	//ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list");
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
<script type="text/javascript">
  function deleteFn(num){
	  location.href="${ctx}/memberDelete.do?num=" + num;
  }
</script>

</head>
<body>
[MVC05 예제 - MyBatis]
<table class="table table-bordered">
  <tr>
    <td>번호</td>
    <td>아이디</td>
    <td>비밀번호</td>
    <td>이름</td>
    <td>나이</td>
    <td>이메일</td>
    <td>전화번호</td>
    <td>삭제</td>
  </tr>
  <c:forEach var="vo" items="${list}">
    <tr>
  	    <td>${vo.num}</td>
  	    <td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
  	    <td>${vo.pass}</td>
  	    <td>${vo.name}</td>
  	    <td>${vo.age}</td>
  	    <td>${vo.email}</td>
  	    <td>${vo.phone}</td>
  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(${vo.num})"></td>
	</tr>
  </c:forEach>
 <%--<%
    MemberVO에 저장된 회원정보를 반복문을 통해 뿌려주는 역할
   	for(MemberVO vo : list) { %>
  		<tr>
	  	    <td><%=vo.getNum() %></td>
	  	    <td><a href="memberContent.do?num=<%=vo.getNum()%>"><%=vo.getId() %></a></td>
	  	    <td><%=vo.getPass() %></td>
	  	    <td><%=vo.getName() %></td>
	  	    <td><%=vo.getAge() %></td>
	  	    <td><%=vo.getEmail() %></td>
	  	    <td><%=vo.getPhone() %></td>
	  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(<%=vo.getNum() %>)"></td>
  	  	</tr>
  <% } %>--%>
  <tr>
    <td colspan="8" align="right"><input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='${ctx}/memberRegister.do'" /></td>
  </tr>
</table>
</body>
</html>