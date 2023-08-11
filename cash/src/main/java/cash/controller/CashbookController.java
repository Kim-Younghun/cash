package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;
import cash.vo.Member;
import cash.service.CounterService;

@WebServlet("/cashbook")
public class CashbookController extends HttpServlet {
	
	private CounterService counterService = null;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { //로그인전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		// 카운터를 request 객체에 넣기
		counterService = new CounterService();
		
		int counter = counterService.getCounter();
		int totalCounter = counterService.getCounterAll();
		
		request.setAttribute("counter", counter);
		request.setAttribute("totalCounter", totalCounter);
		
		
		/*
		//request 전달값
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDay"));
		
		// memberId 호출
		// Member memberOne = (Member)(request.getAttribute("member"));
		// String memberId = memberOne.getMemberId();
		String memberId = "user";
		
		// 모델 호출 (DAO 타겟 월의 수입/지출 데이터)
		List<Cashbook> list = new CashbookDao().selectCashbookListByMonth(memberId, targetYear, targetMonth+1);
		
		// d 값은 이전에 넘겨받은 쿼리스트링에서 가져오며,
	    // 만약 해당 파라미터가 없을 경우 0으로 초기화합니다.
	    int targetDay = request.getParameter("d") != null ? Integer.parseInt(request.getParameter("d")) : 0;
		
	    request.setAttribute("targetDay", targetDay);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
	    */
	    
		// 이번달 달력에 가계부목록의 모델값을 세팅
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
	}
	
}
