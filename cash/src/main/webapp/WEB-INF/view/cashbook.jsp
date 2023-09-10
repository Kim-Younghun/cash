<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>cashbook</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
    
    <script>
	function goBack() {
	  window.history.back();
	}
	</script>
</head>

<body>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="index.html" class="navbar-brand mx-4 mb-3">
                    <h3 class="text-primary">캐시북</h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="${pageContext.request.contextPath}/calendar" class="nav-item nav-link active"><i class="fa fa-wallet me-2"></i>달력</a>
                    <a href="${pageContext.request.contextPath}/memberOne" class="nav-item nav-link"><i class="fa fa-user me-2"></i>회원정보</a>
                    <a href="${pageContext.request.contextPath}/logout" class="nav-item nav-link"><i class="fa fa-sign-out-alt me-2"></i>로그아웃</a>
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->


        <!-- Content Start -->
        <div class="content">

			<!-- Table Start -->
			<div class="container-fluid pt-4 px-4">
			    <div class="row g-4">
			        <div class="col-12">
			            <div class="bg-light rounded h-100 p-4">
			
			                <!-- 입력받은 년/월/일이 있는 경우만 보여줌 -->
			                <c:if test="${targetYear != null && targetMonth != null && targetDay != null }">
			                    <a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${targetDay}"
			                        class="btn btn-primary">내역추가</a>
			                    <a onclick="goBack()" class="btn btn-secondary">뒤로</a>
			                </c:if>
			
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
						</div><!-- end of div with bg-light rounded h-100 p-4 -->
			        </div><!-- end of div with col-sm-12 col-xl-6 -->
			    </div><!-- end of div with row g-4 -->
			</div><!-- end of div with container-fluid pt-4 px-4 -->
			<!-- Table End -->
			
			<div class="container-fluid pt-4 px-4">
			    <div class="row g-4">
			        <div class="col-12">
			            <div class="bg-light rounded h-100 p-4">
					        <div>
					            현재 접속자 : ${currentCounter}
					        </div>
					        <div>
					            오늘 접속자 : ${counter}
					        </div>
					        <div>
					            누적 접속자 : ${totalCounter}
					        </div>
	        			</div><!-- end of div with bg-light rounded h-100 p-4 -->
			        </div><!-- end of div with col-sm-12 col-xl-6 -->
			    </div><!-- end of div with row g-4 -->
			</div><!-- end of div with container-fluid pt-4 px-4 -->

            <!-- Footer Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="bg-light rounded-top p-4">
                    <div class="row">
                        <div class="col-12 col-sm-6 text-center text-sm-start">
                            &copy; <a href="#">Your Site Name</a>, All Right Reserved. 
                        </div>
                        <div class="col-12 col-sm-6 text-center text-sm-end">
                            <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            Designed By <a href="https://htmlcodex.com">HTML Codex</a>
                        </br>
                        Distributed By <a class="border-bottom" href="https://themewagon.com" target="_blank">ThemeWagon</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Footer End -->
        </div>
        <!-- Content End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/chart/chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/easing/easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/waypoints/waypoints.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/tempusdominus/js/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</body>

</html>
     