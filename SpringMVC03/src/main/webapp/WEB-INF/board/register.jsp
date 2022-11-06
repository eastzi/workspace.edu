<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <h2>Board Register</h2>
  <div class="card">
    <div class="card-header">게시물 쓰기</div>
    <div class="card-body">
		<form action="<c:url value='/register.do'/>" method="post">
		    <div class="form-group">
		      <label>Title</label>
		      <input type="text" class="form-control" id="title" name="title">
		    </div>
		    <div class="form-group">
		      <label for="pwd">Text area</label>
		      <textarea class="form-control" rows="3" name="contents"></textarea>
		    </div>
		    <div class="form-group">
		      <label>Writer</label>
		      <input type="text" class="form-control" id="writer" name="writer">
		    </div>
		    <button type="submit" class="btn btn-primary">등록</button>
		    <button type="reset" class="btn btn-warning">취소</button>
	    </form>
	</div> 
    <div class="card-footer">Footer</div>
  </div>
</div>

</body>
</html>