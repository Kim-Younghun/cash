<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addCashbook.jsp</title>
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
           display: flex;
           justify-content: center;
           align-items: center;
           height: 100vh;
           margin: 0;
       }

       .cashbook-form {
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
	<div class="cashbook-form">
        <h1 class="text-center">내역추가</h1>
        <form method="post" action="${pageContext.request.contextPath}/addCashbook">
            <input type="hidden" name="memberId" value="">
            <div class="mb-3">
                <select name="category" class="form-select">
                    <option value="">수입/지출</option>
                    <option value="수입">수입</option>
                    <option value="지출">지출</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">날짜를 입력하세요</label>
                <input type="date" name="cashbookDate" class="form-control">
            </div>
            <div class="mb-3">
                <input type="number" name="price" class="form-control" placeholder="지출내역">
            </div>
            <div class="mb-3">
                <input type="text" name="memo" class="form-control" placeholder="메모사항">
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">내역추가</button>
                <button type="button" class="btn btn-secondary" onclick="goBack()">뒤로</button>
            </div>
        </form>
    </div>
</body>
</html>