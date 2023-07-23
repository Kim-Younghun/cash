<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne.jsp</title>
<!-- Add Bootstrap 5 CSS -->
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
        <h1 class="text-center">회원정보</h1>
        <table class="table table-bordered mt-4">
            <tr>
                <td>아이디: ${member.getMemberId()}</td>
            </tr>
            <tr>
                <!-- * 로 마스크된 패스워드 값 출력 -->
                <td>비밀번호 : ${maskedPw}</td>
            </tr>
            <tr>
                <td>가입일 : ${member.getCreatedate()} </td>
            </tr>
            <tr>
                <td>수정일 : ${member.getUpdatedate()} </td>
            </tr>
        </table>
        <div class="text-center">
	        <a href="${pageContext.request.contextPath}/modifyMember" class="btn btn-primary">회원정보수정</a>
	        <a href="${pageContext.request.contextPath}/removeMember" class="btn btn-danger">회원탈퇴</a>
	        <button onclick="goBack()" class="btn btn-secondary">뒤로</button>
        </div>
    </div>
</body>
</html>