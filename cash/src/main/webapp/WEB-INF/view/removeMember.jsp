<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function goBack() {
  window.history.back();
}
</script>
</head>
<body>
<h1>회원탈퇴</h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
		<table border="1">
			<tr>
				<td>비밀번호를 입력해주세요</td>
				<td><input type="password" name="memberPw"> </td>
			</tr>
			<tr>
				<td>비밀번호를 다시 입력해주세요</td>
				<td><input type="password" name="RememberPw"> </td>
			</tr>
		</table>
		<button type="submit">탈퇴</button>
		<button onclick="goback()">뒤로</button>
	</form>
</body>
</html>