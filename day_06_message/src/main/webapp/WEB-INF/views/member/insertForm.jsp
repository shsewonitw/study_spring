<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<h3>회원정보를 입력하세요</h3>

<form action="<%=request.getContextPath()%>/member/insert" method="post">
<table>
	<caption>회원가입</caption>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="member_id" required>
			<c:if test="${not empty member}"> 
				<span>아이디가 중복되었습니다.</span>
			</c:if>
			
		</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="password" value="${member.password}" required></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="name" value="${member.name}" required></td>
	</tr>
	<tr>
		<th colspan="2"><input type="submit" value="가입"><input type="reset" value="정보 초기화"></th>
	</tr>
</table>
</form>
</body>
</html>