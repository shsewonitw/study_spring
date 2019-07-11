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


<h2>회원정보입력</h2>
<form:form action="step3" modelAttribute="m">
<p>
	<label>아이디:<br/>
	<form:input path="id" />
	</label>
</p>
<p>
	<label>비밀번호:<br/>
	<form:password path="password" />
	</label>
</p>
<p>
	<label>이름:<br/>
	<form:input path="name" />
	</label>
</p>
 <input type="submit" value="가입">
 </form:form>
</body>
</html>