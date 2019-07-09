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

<h2>${ loginMember.name }님의 수신 메세지 (${rList.size()}개)</h2>

<table border="1">
	<tr>
		<th>ID</th>
		<th>보낸사람 아이디</th>
		<th>내용</th>
		<th>보낸시간</th>
		<th>읽은시간</th>		
	</tr>
	<c:forEach items="${rList}" var="rmsg">
	
	<tr>
		<th>${ rmsg.message_id }</th>
		<th>${ rmsg.sender }</th>
		<th><a href="<%=request.getContextPath()%>/message/content/${rmsg.message_id}">내용 확인</a></th>
		<th>${ rmsg.send_time }</th>
		<th>${ empty rmsg.receive_time ? '읽지않음' : rmsg.receive_time  }</th>		
	</tr>
	
	</c:forEach>
</table>
<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>
</body>
</html>














