package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/cashbook");
			return;
		}
		*/
		
		// 쿠키에 저장된 아이디를 request 속성에 저장
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				System.out.println(c.getName()+ ": 쿠키 이름");
				if(c.getName().equals("cookieId") == true) { // 
					request.setAttribute("cookieId", c.getValue());
				}
			}
			
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
		
		// idSave 체크한 경우
		if(request.getParameter("idSave") != null) {
			System.out.println(request.getParameter("idSave") + ": idSave값 확인");
			Cookie cookieIdCookie = new Cookie("cookieId", memberId);
			// cookieIdCookie.setMaxAge(60*60*24); 1일
			response.addCookie(cookieIdCookie);
		}
		
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", loginMember);
		System.out.println("로그인 성공 세션정보"+session.getAttribute("loginMember"));
		response.sendRedirect(request.getContextPath()+"/cashbook");
		
	}
	
}
