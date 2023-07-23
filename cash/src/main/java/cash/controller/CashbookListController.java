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
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/cashbookListByTag")
public class CashbookListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session 유효성 구현
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { //로그인전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member memberOne = (Member)session.getAttribute("loginMember");
		String memberId = memberOne.getMemberId();
		
		// cashbookListByTag.jsp로부터 정렬 반환값 저장
		
		String word = request.getParameter("word");
		if (word == null) {
			word = "";
		}
		
		// cashbookListByTag.jsp로부터 페이징 반환값 저장
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
	        currentPage = Integer.parseInt(request.getParameter("currentPage"));
	    }
		
		int rowPerPage = 10;
		if (request.getParameter("rowPerPage") != null) {
	        currentPage = Integer.parseInt(request.getParameter("rowPerPage"));
	    }
		
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 해시 태그별 가계리스트 조회
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookListByTag(memberId, word, beginRow, rowPerPage);
		
		
		
		// 전체 행 수
		int totalRow = cashbookDao.selectCashbookListCnt(memberId, word);
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage++;
		}
		
		// 페이지네이션 범위
		int pagePerPage = 10;
		int minPage = (((currentPage - 1) / pagePerPage) * pagePerPage) + 1;
		int maxPage = minPage + (pagePerPage - 1);
		// 최대 페이지가 마지막페이지를 넘어가지 못하도록 제한
		if (maxPage > lastPage) {
			maxPage = lastPage;
		}
		
		// 뷰에 값넘기기 (request 속성)
		request.setAttribute("list", list);
		request.setAttribute("word", word);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

}
