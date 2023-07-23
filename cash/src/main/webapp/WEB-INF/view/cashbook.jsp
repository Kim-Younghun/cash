<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbook.jsp</title>
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
        max-width: 800px;
    }

    .table {
        margin-top: 20px;
    }
</style>
</head>
<body>
<div class="container">
        <h1>캐시북</h1>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">로그아웃</a>
        <a href="${pageContext.request.contextPath}/memberOne" class="btn btn-secondary">회원정보</a>
        <a href="${pageContext.request.contextPath}/calendar" class="btn btn-secondary">가계부</a>
        <!-- 입력받은 년/월/일이 있는 경우만 보여줌 -->
        <c:if test="${targetYear != null && targetMonth != null && targetDay != null }">
            <a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}"
                class="btn btn-primary">내역추가</a>
        </c:if>
        <button onclick="goBack()" class="btn btn-secondary">뒤로</button>

        <!-- 상세페이지, 입력받은 년/월/일이 있는 경우만 보여줌 -->
        <c:if test="${targetYear != null && targetMonth != null && targetDay != null }">
            <h1 class="mt-4">${targetYear}년 ${targetMonth + 1}월 ${targetDay}일의 상세페이지</h1>
            <table class="table table-striped mt-3">
                <tr>
                    <th>가계부번호</th>
                    <th>작성자</th>
                    <th>가계내역</th>
                    <th>일자</th>
                    <th>금액</th>
                    <th>메모</th>
                    <th>작성일</th>
                    <th>수정일</th>
                </tr>
                <c:forEach items="${dayList}" var="day">
                    <tr>
                        <td>${day.cashbookNo}</td>
                        <td>${day.memberId}</td>
                        <td>${day.category}</td>
                        <td>${day.cashbookDate}</td>
                        <td>${day.price}</td>
                        <td>${day.memo}</td>
                        <td>${day.createdate}</td>
                        <td>${day.updatedate}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <div class="mt-4">
            현재 접속자 : ${currentCounter}
        </div>
        <div>
            오늘 접속자 : ${counter}
        </div>
        <div>
            누적 접속자 : ${totalCounter}
        </div>
    </div>
</body>
</html>