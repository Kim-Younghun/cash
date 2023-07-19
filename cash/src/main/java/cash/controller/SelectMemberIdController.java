package cash.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cash.model.MemberDao;

@WebServlet("/SelectMemberId")
public class SelectMemberIdController extends HttpServlet {
	
	// aJax post에 반응
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JSON 형식의 응답을 설정
		response.setContentType("application/json");
		PrintWriter out = response.getWriter(); // 응답을 출력하기 위한 PrintWrite 객체 생성
		
		String memberId = request.getParameter("memberId");
		
		MemberDao memberDao = new MemberDao();
		System.out.println("아이디 검사에 사용할 입력받은 ID: "+ memberId);
		// 아이디 중복검사
		int memberIdCk =  memberDao.memberIdCk(memberId); // memberId 중복 체크 결과 반환 (1: 중복, 0: 중복 아님)
		System.out.println(memberIdCk + "<--memberIdCk-- addMemberController"); 
		
		Gson gson = new Gson(); // Gson 객체 생성 (JSON 변환 라이브러리)
		String jsonStr = gson.toJson(memberIdCk); // 결과를 JSON 형식의 문자열로 변환
		
		out.print(jsonStr); // JSON 응답을 출력
		out.flush(); // 출력 버퍼 비우기

	}
}
