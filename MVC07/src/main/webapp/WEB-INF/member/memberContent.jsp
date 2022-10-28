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
<script type="text/javascript">
	function update(){
		document.form1.action="<c:url value='/memberUpdate.do'/>";
		document.form1.submit();
	}
	
	function frmreset() {
		document.form1.reset();
	}

</script>
</head>
<body>
<div class="container">
  <h2>상세화면</h2>
  <div class="card">
    <div class="card-header"><c:if test="${sessionScope.userId != null && sessionScope.userId != ''}">
     <label>${sessionScope.userName}님 로그인 하셨습니다.</label>
     </c:if>
      <c:if test="${sessionScope.userId == null || sessionScope.userId == ''}">
      <label>안녕하세요.</label>
     </c:if>
    </div>
    <div class="card-body">
    <form id="form1" name="form1" class="form-horizantal" action="${ctx}/memberUpdate.do" method="post">
	  <input type="hidden" name="num" value="${vo.num }"/>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">번호:</label>
	  	<div class="col-sm-10">
	  		<c:out value="${vo.num}"/>
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">아이디:</label>
	  	<div class="col-sm-10">
	  		<c:out value="${vo.id}"></c:out>
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">비밀번호:</label>
	  	<div class="col-sm-10">
	  		<c:out value="${vo.pass}"></c:out>
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">이름:</label>
	  	<div class="col-sm-10">
	  		<c:out value="${vo.name}"></c:out>
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">나이:</label>
	  	<div class="col-sm-10">
	  		<input type="text" class="form-control" id="age" name="age" value="${vo.age}" style="width: 10%">
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">이메일:</label>
	  	<div class="col-sm-10">
	  		<input type="text" class="form-control" id="email" name="email" value="${vo.email}" style="width: 30%">
	  	</div>
	  </div>
	  <div class="form-group">
	  	<label class="control-label col-sm-2">전화번호:</label>
	  	<div class="col-sm-10">
	  		<input type="text" class="form-control" id="phone" name="phone" value="${vo.phone}" style="width: 30%">
	  	</div>
	  </div>
	  </form>
	</div> 
    <div class="card-footer" style="text-align: center;">
        <c:if test="${!empty sessionScope.userId }">
        	<c:if test="${sessionScope.userId == vo.id }">
			<input type="button" value="수정하기" class="btn btn-primary" onclick="update()"/>
			</c:if>
			<c:if test="${sessionScope.userId != vo.id }">
			<input type="button" value="수정하기" class="btn btn-primary" onclick="update()" disabled="disabled"/>
			</c:if>
		</c:if>	
	    	<input type="button" value="취소" class="btn btn-warning" onclick="frmreset()"/>
	    	<input type="button" value="리스트" onclick="location.href='${ctx}/memberList.do'" class="btn btn-success"/>
	</div>
  </div>
</div>
</body>
</html>