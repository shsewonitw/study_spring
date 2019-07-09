<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 메세지함</title>
</head>
<body>

<h2></h2>

<table border="1">
	<tr>
		<th>ID</th>
		<th>보낸사람 아이디</th>
		<th>내용</th>
		<th>보낸시간</th>
		<th>읽은시간</th>		
	</tr>

	
	<tr>
		<th>${ message.message_id }</th>
		<th>${ message.sender }</th>
		<th>${ message.content }</th>
		<th>${ message.send_time }</th>
		<th>${ message.receive_time  }</th>		
	</tr>
	

</table>
<h4><a href="<%=request.getContextPath()%>/message/receive">받은 메세지함으로 이동</a></h4>
<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>
</body>
</html>














