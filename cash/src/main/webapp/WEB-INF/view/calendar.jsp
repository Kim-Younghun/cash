<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- jstl substring 호출 -->
<!-- JSP컴파일시 자바코드로 변환되는 c: ...(제어문) 사용  -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar.jsp</title>
<!-- 부트스트랩5 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
<!-- Add Bootstrap 5 JS if needed -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<style>
    /* Custom styles */
    body {
        background-color: #f5f5f5;
        padding-top: 20px;
    }

    .container {
        max-width: 800px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    .month-nav {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }

    .month-nav a {
        display: inline-block;
        margin: 0 10px;
        text-decoration: none;
        color: #007bff;
        font-weight: bold;
    }

    .month-nav a:hover {
        text-decoration: underline;
    }

    .hash-tags {
        margin-bottom: 20px;
    }

    .hash-tags h2 {
        margin-bottom: 10px;
    }

    .hash-tags a {
        display: inline-block;
        margin-right: 10px;
        text-decoration: none;
        color: #333;
        font-weight: bold;
    }

    .calendar-table {
        width: 100%;
        border-collapse: collapse;
    }

    .calendar-table th,
    .calendar-table td {
        text-align: center;
        padding: 10px;
        border: 1px solid #ccc;
    }

    .calendar-table a {
        display: block;
        text-decoration: none;
        color: #333;
        font-weight: bold;
    }

    .calendar-event {
        font-size: 12px;
    }

    .calendar-event .income {
        color: #007bff;
    }

    .calendar-event .expense {
        color: red;
    }
</style>
</head>
<body>
	<!-- 변수값 or 반환값 : EL사용 $ {표현식} -->
	<!-- 속성값대신 EL사용
		ex) request.getAttribute("totalCell") -- requestScope.targetYear (requestScope는 생략가능)
		형변환연산이 필요없음(EL이 자동으로 처리)
	-->
	
	<!-- 자바코드(제어문) : JSTL 사용 -->
	
	<div class="container">
		<h1>${targetYear}년 ${targetMonth+1}월</h1>
		<!-- $ {pageContext.request.contextPath}/CalendarController로 view를 직접호출이 아닌 컨트롤러를 통해 접근한다. -->
		<div class="month-nav">
			<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
			<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
		</div>
		
		<!-- 해시태그 내역 출력 -->
		<div class="hash-tags">
			<h2>이달의 해시태그</h2>
			<div>
				<c:forEach var="m" items="${htList}">
					<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a> <!-- 본인 태그의 수입/지출 내역 출력 - 전체 목록중에서 해당 단어가 출력된다. -->
				</c:forEach>
			</div>
		</div>
		
		
		<table class="calendar-table">
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
			<tr>
				<c:forEach var="i" begin="0" end="${totalCell -1 }" step="1">
					<c:set var="d" value="${i - beginBlank + 1}"></c:set>
				
					<c:if test="${ i!=0 && i%7==0}" >
						<tr></tr>
					</c:if>
					
						<c:if test="${ d > 0 && d <= lastDate}">
							<td>
							<div><a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth}&d=${d}">${d}</a></div>
							
							<c:forEach var="c" items="${list}"> <!-- getter/setter 없을 시 EL/JSTL 사용불가 , c.no는 c.getNo의 의미 get이 생략됨 -->
								<c:if test="${d == fn:substring(c.cashbookDate, 8,10) }"> <!-- 날짜만 자르기 -->
									<div>
										<c:if test="${c.category == '수입' }">
											<span >+${c.price}</span>
										</c:if>
										<c:if test="${c.category == '지출' }">
											<span style="color:red;">-${c.price}</span>
										</c:if>
									</div>
								</c:if>
							</c:forEach>
							</td>
						</c:if>
						<!-- if의 반대 else 조건 -->
						<c:if test="${ !(d > 0 && d <= lastDate)}">
							<td>&nbsp;</td>
						</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>	
</body>
</html>