<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커맨드 객체 사용</title>
</head>
<body>
<%--
	커맨드객체를 사용하여 FORM 태그를 작성하는 커스텀 태그
 --%>
 
 <form:form modelAttribute="m">
 	<form:input path="id"/>
 	<form:password path="password"/>
 	<form:hidden path="name"/>
 </form:form>
</body>
</html>