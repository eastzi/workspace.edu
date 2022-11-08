<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>
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
</head>
<body>
 
<div class="container">
  <h2>Spring MVC01</h2>
  <div class="card">
    <div class="card-header">BOARD</div>
    <div class="card-body">
	  <table class="table">
	    <tr>
	      <td>제목</td>
	      <td>${vo.title}</td>
	    </tr>
	    <tr>
	      <td>내용</td>
	      <td>${fn:replace(vo.content,newLineChar,"<br/>") }</td>
	    </tr>
	    <tr>
	      <td>작성자</td>
	      <td>${vo.writer}</td>
	    </tr>
	    <tr>
	      <td>작성일</td>
	      <td>${fn:split(vo.indate," ")[0]}</td>
	    </tr>
	    <tr>
	      <td colspan="2" align="center">
	        <a href="boardUpdateForm.do/${vo.idx }" class="btn btn-primary">수정화면</a>
	        <a href="boardDelete.do/${vo.idx }" class="btn btn-warning">삭제</a>
	        <a href="boardList.do" class="btn btn-info">목록</a>
	      </td>
	    </tr>
	  </table>
	</div> 
    <div class="card-footer">Footer</div>
  </div>
</div>

</body>
</html>