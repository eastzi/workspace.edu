<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
    	$("#regBtn").click(() => {
    		location.href="<c:url value='/register.do'/>";
    	});
    });
  </script>  
</head>
<body>
 
<div class="container">
  <h2>메인화면</h2>
  <div class="card">
    <div class="card-header">Spring WEB MVC 게시판 만들기
      <button id="regBtn" type="button" class="btn btn-xs pull-right btn-primary" style="display:right">게시물쓰기</button>
    </div>
    
    <div class="card-body">
	    <div class="table-responsive">
		    <table class="table table-bordered">
		      <thead>
		        <tr>
		          <th>게시물번호</th>
		          <th>제목</th>
		          <th>조회수</th>
		          <th>등록자</th>
		          <th>등록일</th>
		        </tr>
		      </thead>
		      <tbody>
		      <c:forEach var="vo" items="${list}">
		        <tr>
		          <td>${vo.idx }</td>
		          <td><a href="<c:url value='/get.do?bno=${vo.idx}'/>">${vo.title }</a></td>
		          <td>${vo.count }</td>
		          <td>${vo.writer }</td>
		          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${vo.indate }"/></td>
		        </tr>
		      </c:forEach>  
		      </tbody>
		    </table>
	    </div>
    </div> 
    <div class="card-footer">화이팅!</div>
  </div>
</div>

</body>
</html>
