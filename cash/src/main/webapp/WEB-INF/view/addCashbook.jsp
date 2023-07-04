<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function goBack() {
  window.history.back();
}
</script>
</head>
<body>
	<h1>내역추가</h1>
	<form method="post" action="${pageContext.request.contextPath}/addCashbook">
		<input type="hidden" name="memberId" value=""> 
		<table border="1">
			<tr>
			<td>
				<select name="category">
					<option value="">수입/지출</option>
					<option value="수입">수입</option>
					<option value="지출">지출</option>
				</select>
			</td>
			</tr>
			<tr>
				<td>
					날짜를 입력하세요
					<input type="date" name="cashbookDate">
				</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="price" placeholder="지출내역">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="memo" placeholder="메모사항">
				</td>
			</tr>
		</table>
		<button type="submit">내역추가</button>
		<button onclick="goback()">뒤로</button>
	</form>
</body>
</html>