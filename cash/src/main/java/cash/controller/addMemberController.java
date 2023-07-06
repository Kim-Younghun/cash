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

@WebServlet("/addMember")
public class addMemberController extends HttpServlet {

	// addMember.jsp 회원가입폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 입력");
		
		// session 인증 검사 코드(null일때)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		
		// signin.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/signin.jsp").forward(request, response); 
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post 입력");
		// session 인증 검사 코드(null일때)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null); // 매개값을 입력받는 생성자를 vo에서 생성했으므로 한번에 코드 작성
		
		
		MemberDao memberDao = new MemberDao();
		
		
		System.out.println("아이디 검사에 사용할 입력받은 ID: "+ memberId);
		// 아이디 중복검사
		boolean memberIdCk =  memberDao.memberIdCk(memberId);
		
		if(memberIdCk) {
			System.out.println("중복된 아이디 입니다. 다른 아이디를 입력해주세요.");
			response.sendRedirect(request.getContextPath()+"/addMember");
			return;
		}
		
		int row = memberDao.insertMember(member);
		
		if(row == 0) { // 회원가입 실패
			System.out.println("회원가입 실패");
			response.sendRedirect(request.getContextPath()+"/addMember"); 
		} else if(row == 1) {
			System.out.println("회원가입  성공");
			response.sendRedirect(request.getContextPath()+"/cashbook"); 
		} else {
			System.out.println("add member error");
		}
	}

}
