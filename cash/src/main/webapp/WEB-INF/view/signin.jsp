<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<script>
function goBack() {
  window.history.back();
}

$(document).ready(function() {
	// 시작시 id 입력폼에 포커스
	$('#id').focus();
	
	let allCheck = false;
	
	// id유효성 체크
	 $('#id').blur(function() {
        if ($('#id').val().length < 4 || $('#id').val().length > 20) {
           $('#idMsg').text('ID는 4~20자이어야 합니다');
           $('#id').focus(); 
        } else {
           console.log($('#id').val()); 
           $('#idMsg').text('');
           $('#pw').focus();
        }
     });
	
	 // pw유효성 체크
     $('#pw').blur(function(){
        if ($('#pw').val().length < 4 || $('#pw').val().length > 20) {
           $('#pwMsg').text('PW는 4~20자이어야 합니다');
           $('#pw').focus();
        } else {
           $('#pwMsg').text('');
           $('#signinBtn').focus();
           allCheck = true;
        }
     });
	 
     // signinBtn click
     $('#signinBtn').click(function() {
        // 페이지에 바로 버턴 누름을 방지하기 위해
        if(allCheck == false) { // if(!allCheck) {
           $('#id').focus();
           return;
        }
        
        $('#signinForm').submit();
     });
});

</script>
</head>
<body>
	<h1>회원가입</h1>
	<form id="signinForm" method="post" action="${pageContext.request.contextPath}/addMember">
		<table border="1">
			<tr>
				<td>아이디 입력하세요</td>
				<td>
					<input id="id" type="text" name="memberId" placeholder="4~20자"> 
					<span id="idMsg" class="msg"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호 입력하세요</td>
				<td>
					<input id="pw" type="password" name="memberPw" placeholder="4~20자">
					<span id="pwMsg" class="msg"></span>
				</td>
			</tr>
		</table>
		<button id="signinBtn" type="submit">회원가입</button>
		<button onclick="goback()">뒤로</button>
	</form>
</body>
</html>