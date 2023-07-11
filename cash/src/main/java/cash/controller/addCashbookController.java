package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;

@WebServlet("/addCashbook")
public class addCashbookController extends HttpServlet {

	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전인 사람은 일정 추가 불가
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		//디버깅
		System.out.println(Integer.parseInt(request.getParameter("targetYear")));
		System.out.println(Integer.parseInt(request.getParameter("targetMonth")));
		System.out.println(Integer.parseInt(request.getParameter("targetDay")));
		
		//request 전달값
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDay"));
		/*
		// 나머지 데이터는 입력폼 이용해서 넘겨받음
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		*/
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
		
	}
	
	
	// 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전인 사람은 일정 추가 불가
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		String memberId = (String)session.getAttribute("loginMember");
		
		// 입력폼으로부터 데이터 받음
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		// 디버깅
		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("cashbookDate"));
		System.out.println(Integer.parseInt(request.getParameter("price")));
		System.out.println(request.getParameter("memo"));
		
		// request 전달값
		Cashbook cashbook = new Cashbook();
		CashbookDao cashbookDao = new CashbookDao();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		// 디버깅
		System.out.println(cashbook.getMemberId());
		System.out.println(cashbook.getCategory() );
		System.out.println(cashbook.getCashbookDate() );
		System.out.println(cashbook.getPrice() );
		System.out.println(cashbook.getMemo() );
		
		int cashbookNo = cashbookDao.insertCashbbok(cashbook); // 키값 반환
		
		System.out.println(cashbookNo);
		
		// 입력실패시
		if(cashbookNo == 0) {
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/addCashbook"); // 매개값 반환해야 함
			return;
		}
		
		// 입력성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복) : 알고리즘
		// 해시태그 추출 알고리즘
		// ##구디 #구디#자바 -> # #구디 #구디 #자바
		HashtagDao hashtagDao = new HashtagDao();
		
		String memo1 = cashbook.getMemo();
		String memo2 = memo1.replace("#", " #"); // "#구디#아카데미" ->" " #구디 #아카데미"
		
		System.out.println("띄어쓰기 적용된 문구 memo2: "+memo2);
		
		// 해시태그가 여러개일시 반복해서 출력
		for(String ht : memo2.split(" ")) {
			String ht2 = ht.replace("#", "");
			if(ht2.length() > 0) {
				Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(ht2);
				
				// 디버깅
				System.out.println(hashtag.getCashbookNo());
				System.out.println(hashtag.getWord() );
				
				hashtagDao.insertHashtag(hashtag);
				
				System.out.println("hashtag DB 입력성공");
			}
		}
		
		// redirect -> cashbookController -> forword -> cashbook.jsp
		response.sendRedirect(request.getContextPath()+"/cashbook");
		return;
		
	}

}
