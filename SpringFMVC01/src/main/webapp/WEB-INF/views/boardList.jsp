<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC01</h2>
  <div class="card">
    <div class="card-header">Board</div>
    <div class="card-body">
		<table class="table table-bordered">
			<tr>
			  <td>번호</td>
			  <td>제목</td>
			  <td>작성자</td>
			  <td>작성일</td>
			  <td>조회수</td>
			</tr>
			<c:forEach var="vo" items="${list}">
			  <tr>
			    <td>${vo.idx}</td>
			    <td><a href="boardContent.do?idx=${vo.idx}">${vo.title}</a></td>
			    <td>${vo.writer}</td>
			    <td>${fn:split(vo.indate," ")[0]}</td>
			    <td>${vo.count}</td>
			  </tr>
			</c:forEach>
		</table>
		<a href="boardForm.do" class="btn btn-primary btn-sm">글쓰기</a>
	</div> 
    <div class="card-footer">Footer</div>
  </div>
</div>

</body>
</html>