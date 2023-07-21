package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;

@WebServlet("/cashbookListController")
public class cashbookListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		// session 유효성 구현
		if(session.getAttribute("loginMember") == null) { //로그인전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		*/
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("loginMember");
		
		// cashbookListByTag.jsp로부터 정렬 반환값 저장
		
		String word = request.getParameter("word");
		if (word == null) {
			word = "";
		}
		
		String searchWord = request.getParameter("searchWord");
		if (searchWord == null) {
			searchWord = "";
		}
		
		String beginYear = request.getParameter("beginYear");
		if (beginYear == null) {
			beginYear = "";
		}
		
		String endYear = request.getParameter("endYear");
		if (endYear == null) {
			endYear = "";
		}
		
		String col = request.getParameter("col");
		if (col == null) {
			col = "";
		}
		
		String ascDesc = request.getParameter("ascDesc");
		if (ascDesc == null) {
			ascDesc = "";
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
		List<Cashbook> list = cashbookDao.selectCashbookListByTag(memberId, word, searchWord, beginYear, endYear, col, ascDesc, beginRow, rowPerPage);
		
		request.setAttribute("list", list);
		
		// 전체 행 수
		int totalRow = cashbookDao.selectCashbookListCnt(memberId, word, searchWord, beginYear, endYear);
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
		
		// 페이징시 넘겨줄 문자값
		String reqString = "";
		reqString += "&rowPerPage=" + rowPerPage +
		    // 한글이 입력될 경우를 대비해서 인코딩
		    "&searchWord=" + URLEncoder.encode(searchWord, "UTF-8") +
		    "&memberId=" + memberId +
		    "&word=" + word +
		    "&beginYear=" + beginYear +
		    "&endYear=" + endYear;
		
		// 뷰에 값넘기기 (request 속성)
		request.setAttribute("reqString", reqString);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
