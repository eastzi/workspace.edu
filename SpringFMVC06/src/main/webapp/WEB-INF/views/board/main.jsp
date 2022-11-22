<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="mvo" value="${SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>  
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC06</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    var csrfHeaderName = "${_csrf.headerName}";
    var csrfTokenValue = "${_csrf.token}";
 
  	$(document).ready(function(){
  		loadList();
  	});
  	
  	function loadList(){
  		//서버와 통신하기 : 게시판 리스트 가져오기 
  		$.ajax({
  			//url : "boardList.do",
  			url : "board/all", //@restController
  			type : "get",
  			dataType : "json",
  			success : makeView,
  			error : function() {alert("error"); }
  		});
  	}
  	                          //        0    1    2
  	function makeView(data) { // data={[ ], [ ], [ ]}
  		// 콜백함수
  		var listHtml="<table class='table table-bordered'>";
  		listHtml+="<tr>";
  		listHtml+="<td>번호</td>";
  		listHtml+="<td>제목</td>";
  		listHtml+="<td>작성자</td>";
  		listHtml+="<td>작성일</td>";
  		listHtml+="<td>조회수</td>";
  		listHtml+="</tr>";
  		
  		$.each(data, function(index, obj){
  			listHtml+="<tr>";
  	  		listHtml+="<td>" + obj.idx + "</td>";
  	  		listHtml+="<td id='t" + obj.idx + "'><a href='javascript:goContent(" + obj.idx + ")'>" + obj.title + "</a></td>";
  	  		listHtml+="<td>" + obj.writer + "</td>";
  	  		listHtml+="<td>" + obj.indate.split(' ')[0] + "</td>"; // 공백 기준으로 자른 후 0번 가져오기
  	  		listHtml+="<td id='cnt" + obj.idx + "'>" + obj.count + "</td>";
  	  		listHtml+="</tr>";
  	  		
  	  		listHtml+="<tr id='c" + obj.idx + "' style='display:none'>";
  	  		listHtml+="<td>내용</td>";
  	  		listHtml+="<td colspan='4'>";
  	  		listHtml+="<textarea id='ta" + obj.idx +"' readonly rows='7' class='form-control'></textarea>";
  	  		//로그인한 회원의 글만 수정, 삭제하기
  	  		if("${mvo.member.memID}" == obj.memID){
	  	  		listHtml+="<br/>";
	  	  		listHtml+="<span id='ud" + obj.idx +"'><button class='btn btn-success' onclick='goUpdateForm("+ obj.idx +")'>수정화면</button></span>&nbsp;"
	  	  		listHtml+="<button class='btn btn-warning' onclick='goDelete("+ obj.idx +")'>삭제</button>";  	  			
  	  		}else{
  	  			listHtml+="<br/>";
	  	  		listHtml+="<span id='ud" + obj.idx +"'><button disabled class='btn btn-success' onclick='goUpdateForm("+ obj.idx +")'>수정화면</button></span>&nbsp;"
	  	  		listHtml+="<button disabled class='btn btn-warning' onclick='goDelete("+ obj.idx +")'>삭제</button>";  	 
  	  		}
	  	  		listHtml+="</td>";
	  	  		listHtml+="</tr>";
  		});
  		
  		//로그인을 한 경우에만 보이는 글쓰기 버튼
  		if(${!empty mvo.member}) {
	  		listHtml+="<tr>";
	  		listHtml+="<td colspan='5'>";
	  		listHtml+="<button class='btn btn-primary' onclick='goForm()'>글쓰기</button>";
	  		listHtml+="</td>";
	  		listHtml+="</tr>";
  		}
	  	listHtml+="</table>";  			
  		
  		$("#view").html(listHtml);	
  		
  		// 등록 후 리스트 보이기
  		$("#view").css("display", "block"); //보이고
  		$("#wfrom").css("display", "none"); //감추고
 
  	}
  	                          
  	function goForm(){
    	$("#view").css("display","none");  // 감추고
    	$("#wfrom").css("display","block");// 보이고
     }        
  	
  	function goList(){
  		$("#view").css("display", "block"); //보이고
  		$("#wfrom").css("display", "none"); //감추고
  	}
  	
  	function goInsert(){
  		/*
  		var title = $("#title").val();
  		var content = $("#content").val();
  		var writer = $("#writer").val();
  		*/
  		
  		var fData = $("#frm").serialize();
  		//alert(fData);
  		$.ajax({
  			//url: "boardInsert.do",
  			url: "board/new", //@restController
  			type: "post",
  			data: fData,
  			beforeSend: function(xhr){
  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
  			success: loadList,
  			error: function(){alert("error");}
  		});
  		
  		/* 글쓰기 등록이 완료되었는데도 글쓰기 페이지에 남아있는 내용 지우기(폼 초기화)
  		$("#title").val("");
  		$("#content").val("");
  		$("#writer").val("");
  		*/
  		
  		// 초기화 시킬 부분이 무수히 많은 경우 취소버튼을 눌러 reset을 하면 한번에 초기화가 되는데
  		// 이 것을 프로그래밍으로 취소버튼을 누르게 하여 전체 초기화 시키는 방법
  		$("#fclear").trigger("click");
  	}
  	
  	// 제목 클릭시 
  	function goContent(idx){
  		// 상세보기 화면이 열릴때
  		if($("#c" + idx).css("display") == "none") {
  			
  			// 제목 클릭시 서버에서 content 가져오기
  			$.ajax({
  				//url: "boardContent.do",
  				url: "board/" + idx,
  				type: "get",
  				//data: {"idx" : idx},
  				dataType: "json",
  				success: function(data) {
  					$("#ta" + idx).val(data.content);
  				},
  				error: function(){alert("error");}
  			});
  			
	  		$("#c" + idx).css("display", "table-row");		
	  		$("#ta" + idx).attr("readonly", true);
  		}else { // 상세보기 화면이 닫힐때
  			$("#c" + idx).css("display", "none");	
  			$.ajax({
  				//url: "boardCount.do",
  				url: "board/count/" + idx,
  				//type: "get",
  				type: "put",
  				//data: {"idx" : idx},
  				dataType: "json",
  				beforeSend: function(xhr){
  	  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
  				success: function(data) {
  					$("#cnt" + idx).text(data.count);
  				},
  				error: function(){alert("error");}
  			})
  		}
  	}
  	
  	function goDelete(idx){
  		$.ajax({
  			//url: "boardDelete.do",
  			url: "board/" + idx,
  			type: "delete",
  			//data: {"idx" : idx}, type: get -> delete로 url에 data를 가져와서 불필요해짐.
  			beforeSend: function(xhr){
  	  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
  			success: loadList,
  			error: function(){alert("error");}
  		});
  	}
  	
  	function goUpdateForm(idx){
  		$("#ta" + idx).attr("readonly", false);
  		var title = $("#t" + idx).text(); // 수정화면 클릭 시 내가 쓴 글의 제목 유지하고 변경할 수 있게 
  		var newInput ="<input type='text' id='nt" + idx +"' class='form-control' value='" + title + "'/>";
  		$("#t" + idx).html(newInput);
  		
  		//수정화면 클릭 시 수정화면 버튼이 수정 버튼으로 변경
  		var newButton = "<button class='btn btn-primary' onclick='goUpdate("+ idx +")'>수정</button>";
  		$("#ud" + idx).html(newButton);
  	}
  	
  	function goUpdate(idx){
  		// 수정된 데이터 값 가져오기
  		var title = $("#nt" + idx).val();
  		var content = $("#ta" + idx).val();
  		$.ajax({
  			//url: "boardUpdate.do",
  			url: "board/update",
  			//type: "post",
  			type: "put",
  			//data: {"idx":idx, "title":title, "content":content}, 문자열을
  			contentType: 'application/json;charset=utf-8', //전달하는 데이터 타입 설정
  			data: JSON.stringify({"idx":idx, "title":title, "content":content}), //JSON형태로 변형
  			beforeSend: function(xhr){
	  				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)},
  			success: loadList,
  			error: function(){alert("error");}
  		})
  	}
  </script>
</head>
<body>
<div class="container">
  <jsp:include page="../common/header.jsp"/> 
  <h3>회원게시판</h3>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="view">Panel Content</div>
    <div class="panel-body" id="wfrom" style="display: none">
     <form id="frm">
     <!-- 회원제게시판을 위해 회원아이디 넘기기 -->
      <input type="hidden" name="memID" id="memID" value="${mvo.member.memID}"/>
      <table class="table">
         <tr>
           <td>제목</td>
           <td><input type="text" id="title" name="title" class="form-control"/></td>
         </tr>
         <tr>
           <td>내용</td>
           <td><textarea rows="7" class="form-control" id="content" name="content"></textarea> </td>
         </tr>
         <tr>
           <td>작성자</td>
           <td><input type="text" id="writer" name="writer" class="form-control" value="${mvo.member.memName}" readonly="readonly"/></td>
         </tr>
         <tr>
           <td colspan="2" align="center">
               <button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
               <button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
               <button type="button" class="btn btn-info btn-sm" onclick="goList()">리스트</button>
           </td>
         </tr>
      </table>
     </form>
    </div>
    <div class="panel-footer">인프런_스프1탄_박매일</div>
  </div>
</div>

</body>
</html>
