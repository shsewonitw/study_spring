<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<c:if test="${not empty member }">
<script type="text/javascript">
	alert("입력된 아이디는 존재하지 않습니다.");
</script>
</c:if>
</head>
<body>

<h3>회원정보를 입력하세요</h3>

<form action="<%=request.getContextPath()%>/member/login" method="post">
<table>
	<caption>로그인</caption>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="member_id" value="${rememberID }" required></td>
		<td><label>아이디기억<input type="checkbox" name="rememberID" value="true"></label></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="password" required></td>
	</tr>
	<tr>
		<th colspan="2"><input type="submit" value="로그인"><input type="reset" value="초기화"></th>
	</tr>
</table>
</form>
</body>
</html>