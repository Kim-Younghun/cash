<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookListByTag.jsp</title>
<!-- 부트스트랩5 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Add Bootstrap 5 JS if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<style>
    body {
        padding: 20px;
    }
    .btn-back {
        margin-bottom: 10px;
    }
    table {
        width: 100%;
    }
    th, td {
        padding: 10px;
        text-align: center;
    }
    th {
        background-color: #f2f2f2;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    a {
    	text-decoration: none;
    }
</style>
<script>
function goBack() {
  window.history.back();
}
</script>
</head>
<body>
	<h1>HashTag List</h1>
	    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">로그아웃</a>
	    <a href="${pageContext.request.contextPath}/memberOne" class="btn btn-primary">회원정보</a>
	    <a href="${pageContext.request.contextPath}/calendar" class="btn btn-primary">가계부</a>
	    <a onclick="goBack()" class="btn btn-secondary">뒤로</a>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>가계부번호</th>
                <th>가계내역</th>
                <th>일자</th>
                <th>금액</th>
                <th>메모</th>
                <th>작성일</th>
                <th>수정일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="tag">
                <tr>
                    <td>${tag.cashbookNo}</td>
                    <td>${tag.category}</td>
                    <td>${tag.cashbookDate}</td>
                    <td>${tag.price}</td>
                    <td>${tag.memo}</td>
                    <td>${tag.createdate}</td>
                    <td>${tag.updatedate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
		<!-- 페이지 네비게이션 -->
		<div class="text-center">
        <c:if test="${minPage > 1}">
                <a  href="${pageContext.request.contextPath}/cashbookListByTag?word=${word}&currentPage=${minPage - pagePerPage}">이전</a>
        </c:if>

        <c:forEach var="i" begin="${minPage}" end="${maxPage}" step="1">
       		<c:if test="${i == currentPage}">
            	${i}
             </c:if>
             <c:if test="${i != currentPage}">
             	<a  href="${pageContext.request.contextPath}/cashbookListByTag?word=${word}&currentPage=${i}">${i}</a>
             </c:if>
        </c:forEach>

        <c:if test="${maxPage != lastPage}">
                <a  href="${pageContext.request.contextPath}/cashbookListByTag?word=${word}&currentPage=${maxPage + pagePerPage}">다음</a>
        </c:if>
        </div>
</body>
</html>