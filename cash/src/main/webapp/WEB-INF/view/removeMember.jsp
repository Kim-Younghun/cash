<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeMember.jsp</title>
<!-- 부트스트랩5 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Add Bootstrap 5 JS if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
function goBack() {
  window.location.href = "${pageContext.request.contextPath}/memberOne";
}
</script>
<style>
    /* Custom styles */
    body {
        background-color: #f5f5f5;
        padding-top: 20px;
    }

    .container {
        max-width: 400px;
    }

    .table {
        margin-top: 20px;
    }
</style>
</head>
<body>
	<div class="container">
        <h1 class="text-center">회원탈퇴</h1>
        <form method="post" action="${pageContext.request.contextPath}/removeMember" class="mt-4">
            <table class="table table-bordered">
                <tr>
                    <td><input type="password" class="form-control" name="memberPw" placeholder="insert password"></td>
                </tr>
                <tr>
                    <td><input type="password" class="form-control" name="RememberPw" placeholder="insert re-enter password"></td>
                </tr>
            </table>
            <div class="d-grid gap-2 mt-3">
                <button type="submit" class="btn btn-danger">탈퇴</button>
                <button type="button" onclick="goBack()" class="btn btn-secondary">뒤로</button>
            </div>
        </form>
    </div>
</body>
</html>