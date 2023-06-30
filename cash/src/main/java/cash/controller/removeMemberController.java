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

@WebServlet("/removeMember")
public class removeMemberController extends HttpServlet {
       
	// 비밀번호 입력(탈퇴) 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	// 탈퇴 액션
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
		String memberPw = request.getParameter("memberPw");
		String RememberPw = request.getParameter("RememberPw");
		
		if(!memberPw.equals(RememberPw)) {
			System.out.println("비밀번호 미일치");
			response.sendRedirect(request.getContextPath()+"/removeMember"); 
			return;
		}
		
		System.out.println("삭제될 ID : "+ loginMember.getMemberId());
		
		Member member = new Member(loginMember.getMemberId(), memberPw, null, null); 
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.removeMember(member);
		
		if(row == 0) { 
			System.out.println("회원탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/removeMember"); 
		} else if(row == 1) {
			System.out.println("회원탈퇴 성공");
			response.sendRedirect(request.getContextPath()+"/login"); 
			// 탈퇴성공
			session.invalidate();
		} else {
			System.out.println("remove member error");
		}
		
	}
}
