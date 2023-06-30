<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%@ page import="cash.model.*" %>
<%
		// member 값 받기		
		Member memberOne = (Member)(request.getAttribute("member"));
		
		// 비밀번호를 스트링 pw로 입력받기
		String pw = memberOne.getMemberPw();
		int len = pw.length(); //비밀번호 길이 계산
		
		//별표 문자열 생성
		StringBuilder sb = new StringBuilder();
		
		// 비밀번호는 20보다 클 수 없음
		if(len > 20) {
			len = 20;
		}
		
		// 비밀번호 개수만큼 *추가
		 for(int i=0; i<len; i++){
		    sb.append("*");
		 }
		 // 출력할 비밀번호 변수
		 String maskedPw=sb.toString(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne.jsp</title>
<script>
function goBack() {
  window.history.back();
}
</script>
</head>
<body>
	<h1>회원정보</h1>
		<table border="1">
			<tr>
				<td>아이디: <%=memberOne.getMemberId() %> </td>
			</tr>
			<tr>
				<!-- * 로 마스크된 패스워드 값 출력 -->
				<td>비밀번호 : <%=maskedPw %> </td>
			</tr>
			<tr>
				<td>가입일 : <%=memberOne.getCreatedate() %> </td>
			</tr>
			<tr>
				<td>수정일 : <%=memberOne.getUpdatedate() %> </td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
		<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
		<button onclick="goback()">뒤로</button>
</body>
</html>