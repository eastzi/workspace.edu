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
  $(document).ready(function(){
	  <c:if test="${!empty msg}">
	  	alert("${msg}");
	  	<c:remove var = "msg" scope="session"/>
	  </c:if>
  });

  function deleteFn(num){
	  location.href="${ctx}/memberDelete.do?num=" + num;
  }
  
  function check(){
	  if($('#user_id').val() == ''){
		  alert("아이디를 입력하세요");
		  return false;
	  }
	  if($('#password').val() == ''){
		  alert("비밀번호를 입력하세요");
		  return false;
	  }
	  return true;
  }
  
</script>

</head>
<body>
[MVC06 예제]
<div class="container">
  <h2>회원관리시스템</h2>
  <div class="card">
    <div class="card-header">
		<div class="container">
		  <form class="form-inline" action="${ctx}/memberLogin.do" method="post">
		    <label for="user_id">ID:</label>
		    <input type="text" class="form-control" id="user_id" placeholder="Enter ID" name="user_id">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		    <button type="submit" class="btn btn-primary" onclick="return check()">로그인</button>
		  </form>
		</div>
	</div>
    <div class="card-body">
	    <div class="table-responsive">
		    <table class="table table-horver">
		      <thead>
		        <tr>
		          	<th>번호</th>
				    <th>아이디</th>
				    <th>비밀번호</th>
				    <th>이름</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
		        </tr>
		      </thead>
		      <tbody>
		        <c:forEach var="vo" items="${list}">
				    <tr>
				  	    <td>${vo.num}</td>
				  	    <td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
				  	    <td>${vo.id}</td>
				  	    <td>${vo.pass}</td>
				  	    <td>${vo.name}</td>
				  	    <td>${vo.age}</td>
				  	    <td>${vo.email}</td>
				  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(${vo.num})"></td>
					</tr>
				</c:forEach>
				<tr>
			      <td colspan="8" align="right"><input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='${ctx}/memberRegister.do'" /></td>
			    </tr>
		      </tbody>
		    </table>
		</div>
    </div> 
    <div class="card-footer">회원관리 ERP System</div>
  </div>
</div>
</table>
</body>
</html>