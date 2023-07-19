package cash.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/addMember")
public class addMemberController extends HttpServlet {

	// signin.jsp 회원가입폼 -> 아이디 중복검사시 doGet으로 오도록 구현
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 입력");
		
		
		// session 인증 검사 코드(로그인한 인원은 들어오지 못하도록 함)
		HttpSession session = request.getSession();
		System.out.println("session.getAttribute(\"loginMember\") :"+session.getAttribute("loginMember"));
		
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
				
		// signup.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/signup.jsp").forward(request, response); 
		return;
	}

	// 회원가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("post 입력");
		
		// session 인증 검사 코드(로그인한 인원은 들어오지 못하도록 함)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 후
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null); // 매개값을 입력받는 생성자를 vo에서 생성했으므로 한번에 코드 작성
		
		MemberDao memberDao = new MemberDao();
		
		// 아이디 중복검사
		int memberIdCk =  memberDao.memberIdCk(memberId);
		
		if(memberIdCk == 1) {
			System.out.println("중복된 아이디 입니다. 다른 아이디를 입력해주세요.");
			response.sendRedirect(request.getContextPath()+"/addMember"); // doGet으로 보내기
			return;
		}
		
		// 회원가입 메서드 호출
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
