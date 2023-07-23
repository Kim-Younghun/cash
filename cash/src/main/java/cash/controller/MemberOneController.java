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

@WebServlet("/memberOne")
public class MemberOneController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) { // 로그인 전
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		// 모델 값 구하기(dao 메서드 호출)
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		
		String pw = member.getMemberPw();
		int len = pw.length(); //비밀번호 길이 계산
		
		//별표 문자열 생성
		StringBuilder sb = new StringBuilder();
		
		// 비밀번호는 20보다 클 수 없음
		if(len > 20) {
			len = 20;
		}
		
		// 비밀번호 개수만큼 *추가
		 for(int i=0; i<len; i++){
		    sb.append("*");
		 }
		 // 출력할 비밀번호 변수
		 String maskedPw=sb.toString(); 
		
		// member 출력하는 (포워딩대상) memberOne.jsp에도 공유되어야 된다.
		// request가 공유되니까 request안에 넣어서 공유
		request.setAttribute("maskedPw", maskedPw);
		request.setAttribute("member", member);
		
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}
