<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 메세지 서비스 제공 사이트</title>
</head>
<body>

<c:if test="${empty sessionScope.loginMember }" var="r">
	<h3><a href="<%=request.getContextPath()%>/member/insert"><spring:message code="regist" /></a></h3>
	<h3><a href="<%=request.getContextPath()%>/member/login"><spring:message code="login" /></a></h3>
</c:if>

<c:if test="${not r}">
	<h3>${loginMember.name } 님 환영합니다.</h3>
	<h3><a href="<%=request.getContextPath()%>/message/receive"><spring:message code="receive_message" arguments="${r_count}" /></a></h3>
	<h3><a href="<%=request.getContextPath()%>/message/send"><spring:message code="send_message" arguments="${s_count }" /></a></h3>
	<h3><a href="<%=request.getContextPath()%>/message/transform"><spring:message code="transform_message" /></a></h3>
	<h3><a href="<%=request.getContextPath()%>/member/logout"><spring:message code="logout" /></a></h3>
</c:if>

</body>
</html>