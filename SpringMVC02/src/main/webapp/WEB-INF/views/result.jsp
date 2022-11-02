<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드된 결과창</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css'>
<script src="https://kit.fontawesome.com/8a96dcfe6b.js" crossorigin="anonymous"></script>
<script src='https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js'></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function getfile(filename){
		location.href="<c:url value='/download.do'/>?filename=" + filename;
	}
</script>
</head>
<body>
<div class="container">
  <h2>업로드가 완료되었습니다.</h2>
  <div class="card">
    <div class="card-header">스프링을 이용한 다중 파일 업로드 구현</div>
    <div class="card-body">
		<table class="table table-bordered table-hover">
			<tr>
				<td>아이디</td>
				<td>${map.id}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${map.name}</td>
			</tr>
			<c:forEach var="fName" items="${map.fileList}">
				<tr>
					<td>${fName}</td>
					<td><a href="javascript:getfile('${fName}')"><i class="fa-solid fa-file"></i></a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center">
					<a href="<c:url value='/form.do'/>">다시 업로드하기</a>
				</td>
			</tr>
		</table>
	</div> 
    <div class="card-footer">화이팅</div>
  </div>
</div>
</body>
</html>