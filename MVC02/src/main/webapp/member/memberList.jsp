<%@page import="kr.bit.model.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.bit.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//MVC01 MemberListController와 동일 형태로 작성 - MVC02에선 out 객체없이 HTML 형태로만 작성가능
	MemberDAO dao = new MemberDAO();
	ArrayList<MemberVO> list = dao.memberList();
%>
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
<script type="text/javascript">
  function deleteFn(num){
	  location.href="memberDelete.jsp?num=" + num;
  }
</script>

</head>
<body>
<table class="table table-bordered">
  <tr>
    <td>번호</td>
    <td>아이디</td>
    <td>비밀번호</td>
    <td>이름</td>
    <td>나이</td>
    <td>이메일</td>
    <td>전화번호</td>
  </tr>
  <%
  //MemberVO에 저장된 회원정보를 반복문을 통해 뿌려주는 역할
  	for(MemberVO vo : list) { %>
  		<tr>
	  	    <td><%=vo.getNum() %></td>
	  	    <td><a href="memberContent.jsp?num=<%=vo.getNum()%>"><%=vo.getId() %></a></td>
	  	    <td><%=vo.getPass() %></td>
	  	    <td><%=vo.getName() %></td>
	  	    <td><%=vo.getAge() %></td>
	  	    <td><%=vo.getEmail() %></td>
	  	    <td><%=vo.getPhone() %></td>
	  	    <td><input type="button" value="삭제" class="btn btn-warning" onclick="deleteFn(<%=vo.getNum() %>)"></td>
  	  	</tr>
  <% } %>
  <tr>
    <td colspan="8" align="right"><input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='memberRegister.html'" /></td>
  </tr>
</table>
</body>
</html>