<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

</head>
<body>

<c:if test="${ login_result }">
	<h3>${ member.name }님 환영합니다.</h3>
	<h4>사이트를 이용하기 위해서 시작 화면으로 이동해주세요</h4>
	<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>
</c:if>

<c:if test="${ not login_result }">
	<h3>로그인에 실패했습니다.</h3>
	<h4>아이디와 패스워를 확인해주세요.</h4>
	<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>
	<h4><a href="<%=request.getContextPath()%>/member/login">로그인 화면으로 이동</a></h4>
</c:if>
</body>
</html>