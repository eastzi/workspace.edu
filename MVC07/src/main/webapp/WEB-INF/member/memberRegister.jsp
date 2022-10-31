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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
	
	function doublecheck() {
		if($("#id").val() == ''){
			alert("아이디를 입력하세요.");
			$("#id").focus();
			return;
		}
		var id = $("#id").val();
		$.ajax({
			url: "<c:url value='/memberDbcheck.do'/>",
			type: "POST", //get or post
			data: {"id" : id}, //페이지 이동하면서 넘기는 데이터 값
			success: dbCheck, //요청이 성공하면 success이벤트 발생 => callback함수 실행
			error: function() {alert("error"); }
		});
	}
	
	function dbCheck(data){
		//data가 있다(null이 아니다) = 중복 -> dao.memberDbcheck 리턴값으로 중복체크
		if(data != "NO") { 
			alert("중복된 아이디입니다.");
			$("#id").focus();
		}else{
			alert("사용가능한 아이디입니다.");
			$("#id").focus();
		}
	}
	
	function add2() {
		if($("#file").val() != '') {
			//파일이 첨부된 경우 -> formData 객체로 넘김
			var formData = new FormData(); 
			formData.append("file", $("input[name = file]")[0].files[0]);
			$.ajax({
				url: "<c:url value='/fileAdd.do'/>", //파일 업로드 컨트롤러
				type: "post",
				data: formData,
				processData: false,
				contentType: false,
				success: function(data){
					//alert(data);
					//업로드된 실제 파일이름을 전달 받기 -> 파일업로드
					$('#filename').val(data); 
					//회원가입 데이터 7개를 insert로 넘기기 -> 회원가입
					document.form1.action="<c:url value='/memberInsert.do'/>?mode=fadd"; //text데이터를 저장하는 부분
					//id, pass, name, age, email, phone, filename(첨부된 경우만)이 memberInsert로 넘어감
					document.form1.submit(); 
				},
				error: function(){alert("error");}
			});
		}else{
			//파일이 첨부되지 않은 경우
			//회원가입 데이터 7개를 insert로 넘기기 -> 회원가입
			//-> 파일 있는경우 없는 경우가 구분이 안됨(memberInsert에서) -> mode로 구분하기
			document.form1.action="<c:url value='/memberInsert.do'/>?mode=add"; 
			//id, pass, name, age, email, phone이 memberInsert로 넘어감
			document.form1.submit(); 
		}
	}
</script>
</head>
<body>
<div class="container">
  <h2>회원가입화면</h2>
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
		      <div>
		        <table>
		          <tr>
		            <td><input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요" name="id"></td>
		            <td><input type="button" value="중복체크" onclick="doublecheck()" class="btn btn-warning"></td>
		          </tr>
		        </table>
		      </div>
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
		      <input type="email" class="form-control" id="phone" placeholder="전화번호를 입력하세요" name="phone">
		    </div>
		    <div class="form-group">
		      <label>첨부파일:</label>
		      <input type="file" class="control-label" id="file" name="file">
		    </div>
		    <input type="hidden" name="filename" id="filename" vlaue="">
	     </form>
	</div> 
    <div class="card-footer" style="text-align: center;">
    	<c:if test="${sessionScope.userId == null || sessionScope.userId == ''}">
			<input type="button" value="등록" class="btn btn-primary" onclick="add2()"/>
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