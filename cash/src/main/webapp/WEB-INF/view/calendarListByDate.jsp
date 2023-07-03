<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 상세페이지 -->
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
</body>
</html>