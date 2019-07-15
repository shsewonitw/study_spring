<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>


<script type="text/javascript">
	$(function(){
		$("#btnJoin").on("click",function(){
		
			var memberObject = new Object();
			memberObject.member_id = $("#member_id").val();
			memberObject.password = $("#password").val();
			memberObject.name = $("#name").val();
			
			var memberJsonObject = JSON.stringify(memberObject);
			$.ajax({
				url : "<%=request.getContextPath()%>/json/ex_07",
				type : "post",
				contentType: "application/json",
				data : memberJsonObject,
				dataType : "json",
				success : function (data) {
					alert("aaaa");
					alert(data.result);
					
				},
				error : function(data) {
					alert("bbbb");
					alert(data.result);
				}
			})
			
		})
		
	})
</script>
<meta charset="UTF-8">
<title>회원가입_JSON</title>
</head>
<body>

<h3>회원정보를 입력하세요</h3>

<form>
<table>
	<caption>회원가입</caption>
	<tr>
		<th>아이디</th>
		<td><input type="text" id="member_id" required></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" id="password" required></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" id="name" required></td>
	</tr>
	<tr>
		<th colspan="2"><button id="btnJoin">가입</button><input type="reset" value="정보 초기화"></th>
	</tr>
</table>
</form>
</body>
</html>