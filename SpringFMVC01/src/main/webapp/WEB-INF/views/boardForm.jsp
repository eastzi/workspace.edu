<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	  <form action="boardInsert.do" method="post">
	    <table class="table">
	      <tr>
	        <td>제목</td>
	        <td><input type="text" name="title" class="form-control" /></td>
	      </tr>
	      <tr>
	        <td>내용</td>
	        <td><textarea rows="7" name="content" class="form-control" ></textarea></td>
	      </tr>
	      <tr>
	        <td>작성자</td>
	        <td><input type="text" name="writer" class="form-control" /></td>
	      </tr>
	      <tr>
	        <td colspan="2" align="center">
	          <button type="submit" class="btn btn-success btn-sm">등록</button>
	          <button type="reset" class="btn btn-warning btn-sm">취소</button>
	        </td>
	      </tr>
	    </table>
	  </form>
	</div> 
    <div class="card-footer">Footer</div>
  </div>
</div>

</body>
</html>