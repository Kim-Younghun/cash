package cash.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;

@WebServlet("/calendarController")
public class calendarController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사
		/*
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { //로그인전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘 날짜
		
		// 첫째날 입력
		firstDay.set(Calendar.DATE, 1);
		
		
		if(request.getParameter("targetYear") != null 
				&& request.getParameter("targetMonth") != null) {
			// targetYear = Integer.parseInt(request.getParameter("targetYear"));
			// targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			firstDay.set(Calendar.YEAR, Integer.parseInt(request.getParameter("targetYear")));
			// API 통해서 targetMonth : 12 입력시 -> 1 입력 , targetYear+1 
			// API 통해서 targetMonth : -1 입력시 -> 12 입력 , targetYear-1 
			firstDay.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth"))); 
		}
		
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		
		// 달력출력시 시작공백 -> 1일날짜의 요일(일1, 월2,...토6) -1 (일요일일경우 공백은 0)
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) -1;
		System.out.println(beginBlank + "<-- beginBlank");
		
		// 출력 월의 마지막일
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate + "<-- lastDate");
		
		// 달력출력시 마지막 날짜 출력 후 공백 개수 -> 전체 출력 셀(totalCell)의 개수가 7로 나누어 떨어져야 한다.
		int endBlank = 0;
		
		if((beginBlank+lastDate) % 7 != 0) {
			endBlank = 7-((beginBlank+lastDate)%7);
			System.out.println(endBlank + "<-- endBlank");
		}
		
		int totalCell = beginBlank+lastDate+endBlank;
		
		System.out.println(totalCell + "<-- totalCell");
		
		// memberId 호출
		// Member memberOne = (Member)(request.getAttribute("member"));
		// String memberId = memberOne.getMemberId();
		String memberId = "user";
		
		// 모델 호출 (DAO 타겟 월의 수입/지출 데이터)
		List<Cashbook> list = new CashbookDao().selectCashbookListByMonth(memberId, targetYear, targetMonth+1);
		
		// int targetDay = Integer.parseInt(request.getParameter("d"));
		
		// d 값은 이전에 넘겨받은 쿼리스트링에서 가져오며,
	    // 만약 해당 파라미터가 없을 경우 0으로 초기화합니다.
	    int targetDay = request.getParameter("d") != null ? Integer.parseInt(request.getParameter("d")) : 0;
	    	
	    System.out.println("targetDay :" + targetDay);
		
	    // 1. `y`, `m`, `d` 변수의 초기값을 0으로 설정합니다.
		// 2. `getParameter()` 메소드를 이용하여 `yParam`, `mParam`, `dParam` 변수에 파라미터 값을 가져옵니다.
		// 3. `yParam`, `mParam`, `dParam` 변수가 `null`이 아니고, 숫자 값인 경우에만 `parseInt()` 메소드를 호출하여 `y`, `m`, `d` 변수에 값을 할당합니다.
		// 4. `matches()` 메소드를 이용하여 파라미터 값이 숫자 값인지 확인합니다. 파라미터 값이 숫자 값이 아닌 경우 `parseInt()` 메소드를 호출할 수 없으므로, 이 과정이 필요합니다.
		int y = 0;
		int m = 0;
		int d = 0;

		String yParam = request.getParameter("targetYear");
		if (yParam != null && yParam.matches("\\d+")) {
		    y = Integer.parseInt(yParam);
		}

		String mParam = request.getParameter("targetMonth");
		if (mParam != null && mParam.matches("\\d+")) {
		    m = Integer.parseInt(mParam) + 1;
		}

		String dParam = request.getParameter("d");
		if (dParam != null && dParam.matches("\\d+")) {
		    d = Integer.parseInt(dParam);
		}

		// 디버깅 확인
		System.out.println(y +"<-- scheduleListByDate param y");
		System.out.println(m +"<-- scheduleListByDate param m");
		System.out.println(d +"<-- scheduleListByDate param d");
		
		// 숫자+문자 = 문자
		// 이 코드는 `m`과 `d` 변수에 저장된 월과 일 정보를 이용하여, 
		// "yyyy-MM-dd" 형식의 문자열을 만들어주는 코드
		String strM = m+"";
		if(m<10) {
			strM = "0"+strM;
		}
		String strD = d+"";
		if(d<10) {
			strD = "0"+strD;
		}
	    
		// 모델 호출 (DAO 타겟 일의 모든 데이터)
		List<HashMap<String, Object>> dayList = new CashbookDao().selectCashbookListByDay(memberId, targetYear, strM, strD);
		
		HashtagDao hashtagdao = new HashtagDao();
		
		
		// 모델 호출 (해시태그별 카운트 데이터)
		List<Map<String, Object>> htList = new HashtagDao().selectWordCountByMonth(memberId, targetYear, targetMonth+1);
		
		// 뷰에 값넘기기 (request 속성)
		request.setAttribute("targetDay", targetDay);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		// 리스트 넣기
		request.setAttribute("list", list);
		
		System.out.println("list :" + list);
		
		// 일별내역 넣기
		request.setAttribute("dayList", dayList);
		
		System.out.println("dayList :" + dayList);
		
		// 해시태그 순위내역 넣기
		request.setAttribute("htList", htList);
		
		System.out.println("htList :" + htList);
		
		if(request.getAttribute("targetDay") == null || targetDay == 0) {
		// 달력을 출력하는 뷰
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
		return;
		}
		
		if(request.getAttribute("targetDay") != null || targetDay != 0) {
			// 일별 내역을 출력하는 뷰
			request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
			return;
		}
		
	}
	
}
