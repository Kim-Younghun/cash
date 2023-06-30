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
	<h1>정보수정</h1>
	<form method="post" action="${pageContext.request.contextPath}/modifyMember">
		<table border="1">
			<tr>
				<td>현재 비밀번호: <input type="password" name="currentMemberPw"> </td>
			</tr>
			<tr>
				<td>변경할 비밀번호: <input type="password" name="memberPw"> </td>
			</tr>
			<tr>
				<td>변경할 비밀번호 재입력: <input type="password" name="RememberPw"> </td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<button onclick="goback()">뒤로</button>
	</form>
</body>
</html>