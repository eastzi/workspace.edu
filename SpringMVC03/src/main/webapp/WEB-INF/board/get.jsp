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
  <script type="text/javascript">
  	$(document).ready(() => {
  		$("#list").click(() => {
  			location.href = "<c:url value='/list.do'/>";
  		});
  		
  		$("#modify").click(() => {
  			//var idx = $("#idx").val();
  			//alert(idx);
  			//location.href="<c:url value='/modify.do'/>?bno=" + idx;
  			location.href="<c:url value='/modify.do'/>?bno=${board.idx}"; //get 방식
  		});

  	});
  </script>
</head>
<body>
 
<div class="container">
  <h2>Board Read</h2>
  <div class="card">
    <div class="card-header">Board Read Page</div>
    <div class="card-body">
    	<div class="form-group">
		    <label>Bno</label>
		    <input type="text" class="form-control" id="idx" name="idx" value="${board.idx}" readonly="readonly">
	    </div>
	    <div class="form-group">
		    <label>Title</label>
		    <input type="text" class="form-control" id="title" name="title" value="${board.title}" readonly="readonly">
	    </div>
	    <div class="form-group">
		    <label>Text area</label>
		    <textarea class="form-control" rows="3" name="contents" readonly="readonly">${board.contents}</textarea>
	    </div>
	    <div class="form-group">
		    <label>Writer</label>
		    <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}" readonly="readonly">
	    </div>
	    <button id="modify" class="btn btn-primary">Modify</button>
	    <button id="list" class="btn btn-success">List</button>
    </div> 
    <div class="card-footer">Footer</div>
  </div>
</div>
</body>
</html>