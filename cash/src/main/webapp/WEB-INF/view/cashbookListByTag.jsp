<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashbookListByTag.jsp</title>
</head>
<body>
<h1>cashbookListByTag.jsp</h1>
		<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
		<a href="${pageContext.request.contextPath}/memberOne">회원정보</a>
		<a href="${pageContext.request.contextPath}/CalendarController">가계부</a>
		<button onclick="goback()">뒤로</button>
		
<!-- 검색 조건 -->
<div>
	<form action="${pageContext.request.contextPath}/cashbookListController" method="get">
		<label>검색 : </label>
		<input type="text" name="searchWord" value="">
		<label>작성 :</label>
		<input type="text" name="beginYear" value=""> 
		~ 
		<input type="text" name="endYear" value="">
		<button type="submit">검색</button>
	</form>
</div>

<table>
	<tr>
		<th>
			가계부번호
			<a href="${pageContext.request.contextPath}/cashbookListController?col=${tag.cashbookNo}&ascDesc=ASC&reqString=${reqString}">[ASC]</a>
			<a href="${pageContext.request.contextPath}/cashbookListController?col=${tag.cashbookNo}&ascDesc=DESC&reqString=${reqString}">[DESC]</a>
		</th>
		<th>
			가계내역
			<a href="${pageContext.request.contextPath}/cashbookListController?col=${tag.cashbookNo}&ascDesc=ASC&reqString=${reqString}">[ASC]</a>
			<a href="${pageContext.request.contextPath}/cashbookListController?col=${tag.cashbookNo}&ascDesc=DESC&reqString=${reqString}">[DESC]</a>
		</th>
		<th>
			일자
			
		</th>
		<th>
			금액
			
		</th>
		<th>
			메모
			
		</th>
		<th>
			수정일
			
		</th>
	</tr>
	<c:forEach items="${list}" var="tag">
		<tr>
			<td>${tag.cashbookNo }</td>
			<td>${tag.memberId }</td>
			<td>${tag.category }</td>
			<td>${tag.cashbookDate }</td>
			<td>${tag.price }</td>
			<td>${tag.memo }</td>
			<td>${tag.createdate }</td>
			<td>${tag.updatedate }</td>
		</tr>
	</c:forEach>
	
	<c:if test="${minPage > 1}">
		<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a>
	</c:if>
	
	<c:forEach var="i" begin="${minPage}" end="${i < maxPage }" step="1">
		<c:if test="${i == currentPage}">
			<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a>
		</c:if>
		
		<c:if test="!${i == currentPage}">
			<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a>
		</c:if>		
	</c:forEach>
</table>
</body>
</html>