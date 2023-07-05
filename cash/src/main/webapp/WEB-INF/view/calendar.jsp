<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %><!-- jstl substring 호출 -->
<!-- JSP컴파일시 자바코드로 변환되는 c: ...(제어문) 사용  -->

<%
	
	/* // request 객체값 가져옴
	int totalCell = (int)(request.getAttribute("totalCell"));
	int beginBlank = (int)(request.getAttribute("beginBlank"));
	int endBlank = (int)(request.getAttribute("endBlank"));
	int lastDate = (int)(request.getAttribute("lastDate"));
	int targetMonth = (int)(request.getAttribute("targetMonth"));
	int targetYear = (int)(request.getAttribute("targetYear"));
	
	// 디버깅
	System.out.println(totalCell);
	System.out.println(beginBlank);
	System.out.println(endBlank);
	System.out.println(lastDate);
	System.out.println(targetMonth);
	System.out.println(targetYear); */
	
%>   

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수값 or 반환값 : EL사용 $ {표현식} -->
	<!-- 속성값대신 EL사용
		ex) request.getAttribute("totalCell") -- requestScope.targetYear (requestScope는 생략가능)
		형변환연산이 필요없음(EL이 자동으로 처리)
	-->
	
	<!-- 자바코드(제어문) : JSTL 사용 -->
	
	<h1>${targetYear}년 ${targetMonth+1}월</h1>
	<!-- $ {pageContext.request.contextPath}/CalendarController로 view를 직접호출이 아닌 컨트롤러를 통해 접근한다. -->
	<a href="${pageContext.request.contextPath}/CalendarController?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
	<a href="${pageContext.request.contextPath}/CalendarController?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
	
	<!-- 해시태그 내역 출력 -->
	<div>
		<h2>이달의 해시태그</h2>
		<div>
			<c:forEach var="m" items="${htList}">
				<a href="${pageContext.request.contextPath}/cashbookListByTag?word=${m.word}">${m.word}(${m.cnt})</a> <!-- 본인 태그의 수입/지출 내역 출력 - 전체 목록중에서 해당 단어가 출력된다. -->
			</c:forEach>
		</div>
	</div>
	
	
	<table border="1">
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
					<div><a href="${pageContext.request.contextPath}/CalendarController?targetYear=${targetYear}&targetMonth=${targetMonth}&d=${d}">${d}</a></div>
					
					<c:forEach var="c" items="${list}"> <!-- getter/setter 없을 시 EL/JSTL 사용불가 , c.no는 c.getNo의 의미 get이 생략됨 -->
						<c:if test="${d == fn:substring(c.cashbookDate, 8,10) }"> <!-- 날짜만 자르기 -->
							<div>
								<c:if test="${c.category == '수입' }">
									<span >+${c.price}</span>
								</c:if>
								<c:if test="${c.category == '지출' }">
									<span style="color:red;">=${c.price}</span>
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
	
	
	
	<%-- <table border="1">
		<thead>
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					// 총 칸수만큼 반복문 출력
					for(int i=0; i<totalCell; i++) {
						// 일주일은 7칸이므로 줄바꿈
						if(i!=0 || i%7==0) {
				%>
							</tr><tr>
				<%		
						}
						
						// 날짜를 출력할 변수와 그 범위 설정
						int dateNum = i - beginBlank + 1;
						if(dateNum > 0 && dateNum <= lastDate) {
				%>
							<td><%=i - beginBlank + 1%></td>
				<%
						} else { // 날짜를 출력할 칸이 아닐경우 공백 출력
				%>
							<td>&nbsp;</td>
				<%			
						}
					}
				%>
			</tr>
		</tbody>
	</table> --%>
</body>
</html>