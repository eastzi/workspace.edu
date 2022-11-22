<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="mvo" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/> <!-- memberUser의 member에 해당 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/> <!-- memberUser의 auth에 해당 -->
<script type="text/javascript">
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	function logout() {
		$.ajax({
			url: "${contextPath}/logout",
			type: "post",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			success: function(){
				location.href = "${contextPath}/";
			},
			error: function(){alert("error");}
		});
	}
</script>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${contextPath}/">스프1탄</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${contextPath}/">Home</a></li>
        <li><a href="boardMain.do">게시판</a></li>            
      </ul>
      <!-- 인증을 하지 않은 경우 -->
      <security:authorize access="isAnonymous()">
	      <ul class="nav navbar-nav navbar-right">
	            <li><a href="${contextPath}/memLoginForm.do"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
	            <li><a href="${contextPath}/memJoin.do"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>            
	      </ul>      
      </security:authorize>
 	  <!-- 인증을 한 경우 -->
  	  <security:authorize access="isAuthenticated()">
	      <ul class="nav navbar-nav navbar-right">
            <li><a href="${contextPath}/memUpdateForm.do"><span class="glyphicon glyphicon glyphicon-wrench"></span> 회원정보수정</a></li>
            <li><a href="${contextPath}/memImageForm.do"><span class="glyphicon glyphicon glyphicon-picture"></span> 사진등록</a></li>
            <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a></li>      
		  	<c:if test="${empty mvo.member.memProfile}"> <!-- Profile이 null인 경우 -->
		  		<li><img class="img-circle" alt="기본이미지" src="${contextPath}/resources/images/person.png" style="width: 50px; height: 50px"/>
		  	</c:if>
		  	<c:if test="${!empty mvo.member.memProfile}"> <!-- Profile이 null이 아닌 경우 -->
		  		<li><img class="img-circle" alt="기본이미지" src="${contextPath}/resources/images/${mvo.member.memProfile}" style="width: 50px; height: 50px"/>
		  	</c:if>
		  	  ${mvo.member.memName}님
		  	  ( <!-- 회원 권한 표시 -->
		  	  <security:authorize access="hasRole('ROLE_USER')">
		  	    U,
		  	  </security:authorize>
		  	  <security:authorize access="hasRole('ROLE_MANAGER')">
		  	    M,
		  	  </security:authorize>
		      <security:authorize access="hasRole('ROLE_ADMIN')">
		  	    A
		  	  </security:authorize>
		  	  )
		  	</li>
	      </ul>
      </security:authorize>
    </div>
  </div>
</nav>