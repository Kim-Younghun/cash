<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
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
		<a href="${pageContext.request.contextPath}/CalendarController">가계부</a>
		<!-- 입력받은 년/월/일이 있는경우만 보여줌-->
		<c:if test="${targetYear != null && targetMonth != null && targetDay != null }">
		<a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}">내역추가</a>
		</c:if>	
		<button onclick="goback()">뒤로</button>
<!-- 상세페이지, 입력받은 년/월/일이 있는경우만 보여줌-->
<c:if test="${targetYear != null && targetMonth != null && targetDay != null }">
<h1>${targetYear}년 ${targetMonth+1}월 ${targetDay}일의 상세페이지</h1>
<table>
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
			<td>${day.cashbookNo }</td>
			<td>${day.memberId }</td>
			<td>${day.category }</td>
			<td>${day.cashbookDate }</td>
			<td>${day.price }</td>
			<td>${day.memo }</td>
			<td>${day.createdate }</td>
			<td>${day.updatedate }</td>
		</tr>
	</c:forEach>	
</table>
</c:if>
</body>
</html>