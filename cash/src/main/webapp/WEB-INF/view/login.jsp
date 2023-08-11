<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>login.jsp</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

	<!-- 부트스트랩5 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
<script>
	function goBack() {
	  window.history.back();
	}
</script>
</head>

<body>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <%-- <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End --> --%>

	<%-- <div class="login-form">
        <h1 class="mb-4 text-center">로그인</h1>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="mb-3">
                <label for="memberId" class="form-label">ID</label>
                <input type="text" class="form-control" name="memberId" id="memberId" value="${cookieId}" required>
                 ID저장 <input type="checkbox" name="idSave">
            </div>
            <div class="mb-3">
                <label for="memberPw" class="form-label">PASSWORD</label>
                <input type="password" class="form-control" name="memberPw" required>
            </div>
            <button type="submit" class="btn btn-primary">로그인</button>
            <a href="${pageContext.request.contextPath}/addMember" class="btn btn-primary" id="signUpButton">회원가입</a>
            <button type="button" class="btn btn-secondary" onclick="goBack()">뒤로</button>
        </form>
    </div> --%>
    
        <!-- Sign In Start -->
        <div class="container-fluid">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                            <a href="index.html" class="">
                                <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>DASHMIN</h3>
                            </a>
                            <h3>login</h3>
                        </div>
                      <form method="post" action="${pageContext.request.contextPath}/login">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="memberId" id="memberId" value="${cookieId}" 
                            placeholder="insert your id" required="required">
                            <label for="floatingInput">ID</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="floatingPassword" name="memberPw" required
                            placeholder="Password">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" name="idSave" id="exampleCheck1">
                                <label class="form-check-label" for="exampleCheck1">ID저장</label>
                            </div>
                            <!-- <a href="">Forgot Password</a> -->
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">login</button>
                        <p class="text-center mb-0">Don't have an Account? <a href="${pageContext.request.contextPath}/addMember" id="signUpButton">Sign Up</a></p>
                        <button type="button" class="btn btn-primary py-3 w-100 mb-4" onclick="goBack()">뒤로</button>
                      </form>  
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
    </div>
	
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>
