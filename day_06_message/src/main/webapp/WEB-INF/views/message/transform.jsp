<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 전송 페이지</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>

<script type="text/javascript">
	
	$(document).ready(function() {
		$("#searchID").on("keyup", function() {
			//console.log($("#searchID").val())
			
			var searchID = $("#searchID").val();
			
			if( searchID == '' ) {
				$("#searchIDResult").empty();
				return;
			}				
			
			$.ajax({
				url : "<%=request.getContextPath()%>/message/searchID",
				type : "post",
				data : "searchID="+searchID,
				dataType : "json",
				success : function (data) {
					//alert(data)
					
					$("#searchIDResult").empty();		
					
					var appendTag = "";
						
					$.each(data, function(index, value) {
						appendTag += "<p><label><input type='radio' name='searchIDs' class='searchIDs' value='" + value.member_id + "'>" + value.member_id + "</label></p>";
						//alert(index + " - " + value.member_id)
					})
					
					$("#searchIDResult").append(appendTag);
					
					/*
					//$("#searchIDResult").empty();					
					var appendTag = 
						"<p><label><input type='radio' name='searchIDs' class='searchIDs' value='" + data + "'>" + data + "</label></p>";
					$("#searchIDResult").append(appendTag);
					*/
				},
				error : function(data) {
					alert(data)
				}
			})
			
		});
		
		// 동적 바인딩을 활용한 이벤트 처리
		// - 동적으로 추가되는 문서 요소에 대해서도
		//   이벤트를 처리하기 위한 방법
		$(document).on("click", ".searchIDs", function() {
			$("#receiver").val($(this).val())
		})
	});
	
</script>

</head>
<body>

<table>
	<tr>
		<th>아이디검색</th>
		<th><input type="text" id="searchID"></th>		
	</tr>
	<tr>
		<td colspan="2" id="searchIDResult">
			검색결과 없음
		</td>
	</tr>
</table>

<form action="<%=request.getContextPath()%>/message/transform" method="post">	
	<input type="hidden" name="sender" value="${loginMember.member_id}">
	
<table>
	<tr>
		<th>받는사람</th>
		<td><input type="text" name="receiver" id="receiver"></td>
	</tr>	
	<tr>
		<th>메세지</th>
		<td>
			<textarea rows="15" cols="30" name="content"></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="전송">
			<input type="reset" value="초기화">
		</th>		
	</tr>	
</table>

</form>

</body>
</html>










