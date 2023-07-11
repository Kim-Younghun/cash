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

@WebServlet("/login")
public class loginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response); // 로그인 get 요청시 해당 url로 이동
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null); // 매개값을 입력받는 생성자를 vo에서 생성했으므로 한번에 코드 작성
		
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
		
		if(loginMember == null) { // 로그인 실패
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/login"); // controller를 가리킴.
			
			return;
		} 
		
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", loginMember);
		System.out.println("로그인 성공 세션정보"+session.getAttribute("loginMember"));
		response.sendRedirect(request.getContextPath()+"/cashbook");
		
	}
	
}
