<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<c:if test="${ insert_result }">
	<h3>${ member.name }님 회원가입에 성공했습니다.</h3>
	<h4><spring:message code="require_login" /></h4>
	<h4><a href="<%=request.getContextPath()%>/member/login">로그인 화면으로 이동</a></h4>
</c:if>

<c:if test="${ not insert_result }">
	<h3>${ member.name }회원가입 처리과정에서 문제가 발생하였습니다.</h3>
	<h4>관리자에게 문의해주세요</h4>
	<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>
</c:if>
</body>
</html>