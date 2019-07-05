<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
<h2>약관</h2>
<p>약관 내용</p>
<h1>멤버 이름 : ${member.name }</h1>
<form action="<%=request.getContextPath() %>/register/step2" method="post">
	<label>
		<input type="checkbox" name="agree" value="true"> 약관 동의
	</label>
	<div>
		이름: <input type="text" name="name"><br/> 
		나이: <input type="text" name="age">
	</div>
	<input type="submit" value="다음 단계" />
</form>
</body>
</html>