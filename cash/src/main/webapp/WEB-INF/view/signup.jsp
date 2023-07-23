<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
<!-- Add Bootstrap 5 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Add Bootstrap 5 JS if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
function goBack() {
  window.history.back();
}

/*
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
*/


$(document).ready(function(){
	  $('#memberId').change(function(){ // 아이디 입력란에서 값 변경 시
	      var memberId = $(this).val(); // 현재 입력된 값을 가져옴

	      $.ajax({
	          url: '${pageContext.request.contextPath}/selectMemberId', //아이디 중복검사가 있는 서블릿 주소 
	          data: { memberId : memberId }, // 서버로 보낼 데이터
	          type: 'post',
	          dataType:'json',
	          
	           success:function(data){
	              if(data == 1){ 
	                  $('#message').text('중복된 ID입니다.'); // 메시지 출력
	                  $("#signupBtn").attr("disabled", true);   // 회원가입 버튼 비활성화 (선택 사항)
	              } else {
	                  $('#message').text('사용 가능한 ID입니다.');
	                   $("#signupBtn").removeAttr("disabled");   // 회원가입 버튼 활성화 (선택 사항)
	               }
	            }
	        });
	     });
	});


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

        .signup-form {
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
	<div class="signup-form">
        <h1 class="text-center mb-4">회원가입</h1>
        <form id="signupForm" method="post" action="${pageContext.request.contextPath}/addMember">
            <div class="mb-3">
                <label for="memberId" class="form-label">아이디 입력하세요</label>
                <input id="memberId" type="text" class="form-control" name="memberId" placeholder="4~20자">
                <span id="message"></span>
            </div>
            <div class="mb-3">
                <label for="memberPw" class="form-label">비밀번호 입력하세요</label>
                <input id="memberPw" type="password" class="form-control" name="memberPw" placeholder="4~20자">
            </div>
            <div class="d-grid gap-2">
                <button id="signupBtn" type="submit" class="btn btn-primary" disabled>회원가입</button>
                <button type="button" class="btn btn-secondary" onclick="goBack()">뒤로</button>
            </div>
        </form>
    </div>
</body>
</html>