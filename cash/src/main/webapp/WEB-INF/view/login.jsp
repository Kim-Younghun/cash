<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<!-- 부트스트랩5 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Add Bootstrap 5 JS if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
function goBack() {
  window.history.back();
}

</script>
 <style>
        /* Custom styles */
        body {
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-form {
            max-width: 360px;
            padding: 15px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
	<div class="login-form">
        <h1 class="mb-4 text-center">로그인</h1>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="mb-3">
                <label for="memberId" class="form-label">ID</label>
                <input type="text" class="form-control" name="memberId" id="memberId" value="${cookieId}" required>
                 ID저장 <input type="checkbox" name="idSave">
            </div>
            <div class="mb-3">
                <label for="memberPw" class="form-label">PASSWORD</label>
                <input type="password" class="form-control" name="memberPw" required>
            </div>
            <button type="submit" class="btn btn-primary">로그인</button>
            <a href="${pageContext.request.contextPath}/addMember" class="btn btn-primary" id="signUpButton">회원가입</a>
            <button type="button" class="btn btn-secondary" onclick="goBack()">뒤로</button>
        </form>
    </div>
</body>
</html>