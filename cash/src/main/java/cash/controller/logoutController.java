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

@WebServlet("/logout")
public class logoutController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		System.out.println("로그아웃 성공");
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/login");
	}
	
}
