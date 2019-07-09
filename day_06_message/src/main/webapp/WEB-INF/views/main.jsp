<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 메세지 서비스 제공 사이트</title>
</head>
<body>

<c:if test="${empty sessionScope.loginMember }" var="r">
	<h3><a href="<%=request.getContextPath()%>/member/insert">회원가입</a></h3>
	<h3><a href="<%=request.getContextPath()%>/member/login">로그인</a></h3>
</c:if>

<c:if test="${not r}">
	<h3>${loginMember.name } 님 환영합니다.</h3>
	<h3><a href="<%=request.getContextPath()%>/message/receive">받은 메세지(${ r_count })</a></h3>
	<h3><a href="<%=request.getContextPath()%>/message/send">보낸 메세지(${ s_count })</a></h3>
	<h3><a href="<%=request.getContextPath()%>/message/transform">메세지 전송</a></h3>
	<h3><a href="<%=request.getContextPath()%>/member/logout">로그아웃</a></h3>
</c:if>

</body>
</html>