<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbook.jsp</title>
<script>
function goBack() {
  window.history.back();
}
</script>
</head>
<body>
<h1>cashbook.jsp</h1>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne">회원정보</a>
		<button onclick="goback()">뒤로</button>
</body>
</html>