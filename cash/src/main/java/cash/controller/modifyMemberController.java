package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/modifyMember")
public class modifyMemberController extends HttpServlet {

	// modifyMember 회원수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
		
	}

	// modifyMember 회원수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		// 세션객체에서 id값 끄집어내기
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		loginMember.getMemberId();
		String currentMemberPw = request.getParameter("currentMemberPw");
		String memberPw = request.getParameter("memberPw");
		String RememberPw = request.getParameter("RememberPw");
		
		if(!memberPw.equals(RememberPw)) {
			System.out.println("비밀번호 미일치");
			response.sendRedirect(request.getContextPath()+"/modifyMember"); 
			return;
		}
		
		System.out.println("수정할 ID : "+ loginMember.getMemberId());
		
		Member member = new Member(loginMember.getMemberId(), memberPw, null, null); 
		
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.modifyMember(member, RememberPw);
		
		if(row == 0) { 
			System.out.println("회원수정 실패");
			response.sendRedirect(request.getContextPath()+"/modifyMember"); 
		} else if(row == 1) {
			System.out.println("회원수정 성공");
			response.sendRedirect(request.getContextPath()+"/login"); 
			// 수정성공
			session.invalidate();
		} else {
			System.out.println("modyfy member error");
		}
		
		
	}

}
