<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 메세지함</title>
</head>
<body>

<h2>${ loginMember.name }님이 발신 메세지 (${r_count}개)</h2>

<table>
	<tr>
		<th>ID</th>
		<th>받은사람 아이디</th>
		<th>내용</th>
		<th>보낸시간</th>
		<th>읽은시간</th>		
	</tr>
	<c:forEach items="${rList}" var="rmsg">
	
	<tr>
		<th>${ rmsg.message_id }</th>
		<th>${ rmsg.receiver }</th>
		<th><a href="<%=request.getContextPath()%>/message/send_content/${rmsg.message_id}">내용확인</a></th>
		<th>${ rmsg.send_time }</th>
		<th>${ empty rmsg.receive_time ? '읽지않음' : rmsg.receive_time }</th>		
	</tr>
	
	</c:forEach>
	
	<tr>
		<th colspan="5">
		
		<c:if test="${beforePageNo ne -1}">
			<a href="<%=request.getContextPath()%>/message/send/${beforePageNo}">이전</a>
		</c:if>
		
		<c:forEach var="pageNo" begin="${startPageNo}" end="${endPageNo}">
			<c:if test="${ curPage eq pageNo }" var="r">
				[${pageNo }]
			</c:if>
			<c:if test="${not r }">
				<a href="<%=request.getContextPath()%>/message/send/${pageNo}">${pageNo}</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${afterPageNo ne -1}">
			<a href="<%=request.getContextPath()%>/message/send/${afterPageNo}">다음</a>
		</c:if>
		
		</th>
	</tr>
</table>

<h4><a href="<%=request.getContextPath()%>">시작 화면으로 이동</a></h4>

</body>
</html>














