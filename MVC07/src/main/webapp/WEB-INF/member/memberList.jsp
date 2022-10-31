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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style type="text/css">
	table td {
		vertical-align: middle !important;
	}
</style>
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
  
  function logout() {
	location.href="<c:url value='/memberLogout.do'/>"; //=${ctx}/memberLogout.do 동일
  }
  
  function memberList() {
	/* var html = $("#collapse1 .card-body").html();
	alert(html) */
	$.ajax({
		url: "<c:url value='/memberAjaxList.do'/>", //해당서버로요청
		type: "get",
		dataType: "json",
		success: resultHtml, //회원리스트로받기([{ }, { }, { } ..])
		error: function(){error("error");}
	})
  }
  
  function resultHtml(data) {
	  
	var html = "<table class='table table-hover'>";
	html+="<tr>";
	html+="<th>번호</th>";
	html+="<th>아이디</th>";
	html+="<th>비밀번호</th>";
	html+="<th>이름</th>";
	html+="<th>나이</th>";
	html+="<th>이메일</th>";
	html+="<th>전화번호</th>";
	html+="<th>삭제</th>";
	html+="</tr>";
	//데이터가 들어오면 반복문처리
	$.each(data, function(index, obj){
		html+="<tr>";
		html+="<td>" + obj.num + "</td>";
		html+="<td>" + obj.id + "</td>";
		html+="<td>" + obj.pass + "</td>";
		html+="<td>" + obj.name + "</td>";
		html+="<td>" + obj.age + "</td>";
		html+="<td>" + obj.email + "</td>";
		html+="<td>" + obj.phone + "</td>";
		html+="<td><input type='button' value='삭제' class='btn btn-warning' onclick='delFn("+obj.num+")'></td>";
		html+="</tr>";
	}); 
	html+="</table>";
	
	$("#collapseOne .card-body").html(html);
  }
	
  function delFn(num) {
	$.ajax({
		url: "<c:url value='/memberAjaxDelete.do'/>",
		type:"get",
		data: {"num" : num},
		success: memberList,
		error: function(){alert("error");}
	});
  }
  
</script>

</head>
<body>
[MVC07 예제]
<div class="container">
  <h2>회원관리시스템</h2>
  <div class="card">
    <div class="card-header">
		<div class="container">     <!-- requestScope - request.setAttribute / sessionScope - session.setAttribute -->
		<c:if test="${sessionScope.userId == null || sessionScope.userId == ''}"> <!-- || 하나만 참이라도 참 -->
		  <form class="form-inline" action="${ctx}/memberLogin.do" method="post">
		    <label for="user_id">ID:</label>
		    <input type="text" class="form-control" id="user_id" placeholder="Enter ID" name="user_id">
		    <label for="pwd">Password:</label>
		    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		    <button type="submit" class="btn btn-primary" onclick="return check()">로그인</button>
		  </form>
		</c:if>
		<c:if test="${sessionScope.userId != null && sessionScope.userId != ''}"> <!-- && 둘다 모두 참 -->
		${sessionScope.userName}님 환영합니다.
		<button type="button" class="btn btn-warning" onclick="logout()">로그아웃</button>
		</c:if>
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
				    <th>이미지</th>
				    <th>삭제</th>
		        </tr>
		      </thead>
		      <tbody>
		        <c:forEach var="vo" items="${list}">
				    <tr>
				  	    <td>${vo.num}</td>
				  	    <td><a href="${ctx}/memberContent.do?num=${vo.num}">${vo.id}</a></td>
				  	    <td>${vo.pass}</td>
				  	    <td>${vo.name}</td>
				  	    <td>${vo.age}</td>
				  	    <td>${vo.email}</td>
				  	    <td>${vo.phone}</td>
				  	    <td>
				  	      <c:if test="${vo.filename != null && vo.filename != ''}">
				  	        <img src="<c:out value='file_repo/${vo.filename}'/>" width="60px" height="60px">
				  	      </c:if>
				  	    </td>
				  	    <c:if test="${sessionScope.userId == vo.id }">
				  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(${vo.num})"></td>
				  	    </c:if>
				  	    <c:if test="${sessionScope.userId != vo.id }">
				  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(${vo.num})" disabled="disabled"></td>
						</c:if>
					</tr>
				</c:forEach>
				<tr>
			      <td colspan="9" align="right"><input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='${ctx}/memberRegister.do'" /></td>
			    </tr>
		      </tbody>
		    </table>
		</div>
    </div> 
    <div class="card-footer">회원관리 ERP System</div>
  </div>
</div>
<div id="accordion">
    <div class="card" style="width: 80%">
      <div class="card-header">
        <a class="card-link" data-toggle="collapse" href="#collapseOne" onclick="memberList()">
          회원리스트보기
        </a>
      </div>
      <div id="collapseOne" class="collapse show" data-parent="#accordion">
        <div class="card-body">
          body
        </div>
      </div>
    </div>
  </div>
</table>
</body>
</html>