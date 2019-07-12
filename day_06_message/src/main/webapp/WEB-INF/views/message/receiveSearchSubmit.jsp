<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 메세지함</title>
</head>
<body>

<h2>검색결과에 해당되는 메세지 (${searchedCount}개)</h2>

<table>
	<tr>
		<th>ID</th>
		<th>보낸사람 아이디</th>
		<th>내용</th>
		<th>보낸시간</th>
		<th>읽은시간</th>		
	</tr>
	<c:forEach items="${searched}" var="message">
	
	<tr>
		<th>${ message.message_id }</th>
		<th>${ message.sender }</th>
		<th><a href="<%=request.getContextPath()%>/message/content/${message.message_id}">내용확인</a></th>
		<th>${ message.send_time }</th>
		<th>${ empty message.receive_time ? '읽지않음' : message.receive_time }</th>		
	</tr>	
	</c:forEach>	
</table>

<form:form>
	<p>메세지 받은 날자(시작) : 
	<form:input path="from"/>
	</p>
	<p>
	메세지 받은 날자(종료) :
	<form:input path="to"/>
	</p>
	<p>
		<input type="submit" value="검색">
		<input type="reset" value="초기화">
	</p>
</form:form>

<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>

</body>
</html>














