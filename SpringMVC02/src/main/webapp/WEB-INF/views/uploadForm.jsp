<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">
	var cnt = 1;
	function file_add() {
		$("#d_file").append("<br>" + "<input type='file' name='file"+cnt+"'/>");
		cnt++;
	}

</script>
</head>
<body>
<div class="container">
  <h2>다중파일 업로드</h2>
  <div class="card">
    <div class="card-header">스프링을 이용한 다중 파일 업로드 구현</div>
    <div class="card-body">
	    <form action="<c:url value='/upload.do'/>" enctype="multipart/form-data" method="post">
		    <div class="form-group">
		      <label for="id">아이디:</label>
		      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id" style="width: 30%">
		    </div>
		    <div class="form-group">
		      <label for="name">이름:</label>
		      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" style="width: 30%">
		    </div>
		    <div class="form-group">
		      <label for="">파일추가:</label>
		      <input type="button" value="파일추가" onclick="file_add()"><br>
		      <div id="d_file"></div>
		    </div>
		    
		    <button type="submit" class="btn btn-primary">업로드</button>
	    </form>
    </div> 
    <div class="card-footer">화이팅!</div>
  </div>
</div>
</body>
</html>