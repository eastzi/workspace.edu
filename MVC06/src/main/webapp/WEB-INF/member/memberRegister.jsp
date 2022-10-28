<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	function add() {
		//form 데이터 유효성 체크
		
		//form1 데이터 전송
		document.form1.action="<c:url value='/memberInsert.do'/>";
		document.form1.submit();
	}
	
	function frmreset() {
		document.form1.reset();
	}
</script>
</head>
<body>
<div class="container">
  <h2>Card Header and Footer</h2>
  <div class="card">
    <div class="card-header">
	  <c:if test="${sessionScope.userId != null && sessionScope.userId != ''}">
	    <label>${sessionScope.userName}님이 로그인 하셨습니다.</label>
	  </c:if>
	  <c:if test="${sessionScope.userId == null || sessionScope.userId == ''}">
	    <label>안녕하세요.</label>
	  </c:if>
	</div>
    <div class="card-body">
		 <form id="form1" name="form1" method="post">
		    <div class="form-group">
		      <label for="email">아이디:</label>
		      <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요" name="id">
		    </div>
		    <div class="form-group">
		      <label for="pwd">비밀번호:</label>
		      <input type="password" class="form-control" id="pass" placeholder="비밀번호를 입력하세요" name="pass">
		    </div>
		    <div class="form-group">
		      <label for="name">이름:</label>
		      <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요" name="name">
		    </div>
		    <div class="form-group">
		      <label for="age">나이:</label>
		      <input type="text" class="form-control" id="age" placeholder="나이를 입력하세요" name="age">
		    </div>
		    <div class="form-group">
		      <label for="email">이메일:</label>
		      <input type="email" class="form-control" id="email" placeholder="이메일을 입력하세요" name="email">
		    </div>
		    <div class="form-group">
		      <label for="phone">전화번호:</label>
		      <input type="email" class="form-control" id="phone" placeholder="이메일을 입력하세요" name="phone">
		    </div>
	     </form>
	</div> 
    <div class="card-footer" style="text-align: center;">
    	<c:if test="${sessionScope.userId == null || sessionScope.userId == ''}">
			<input type="button" value="등록" class="btn btn-primary" onclick="add()"/>
		</c:if>
		
		<c:if test="${sessionScope.userId != null && sessionScope.userId != ''}">
			<input type="button" value="등록" class="btn btn-primary" onclick="add()" disabled="disabled"/>
		</c:if>
		
		<input type="button" value="취소" class="btn btn-warning" onclick="frmreset()"/>
		<input type="button" value="리스트" onclick="location.href='${ctx}/memberList.do'" class="btn btn-success"/>
	</div>
  </div>
</div>
</body>
</html>