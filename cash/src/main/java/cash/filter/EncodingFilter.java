package cash.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*") // 모든 요청에 대해 적용됨
public class EncodingFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 웹 요청일 경우에만 인코딩 실행
		if(request instanceof HttpServletRequest) {
			request.setCharacterEncoding("utf-8");
			
			System.out.println("utf-8 필터 적용 완료 : ");
			 
		}
		
		chain.doFilter(request, response);
		
		/*
		String uri = httpRequest.getRequestURI();
		
		if(uri.endsWith("/login") || uri.endsWith("/addMember")
				) { // 로그인 페이지 및 특정 조건의 세션 유효성의 경우 세션 검사를 건너띄고 개별적으로 처리
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = httpRequest.getSession(true); // 세션이 존재하지 않으면 새로운 세션을 생성하고, 이미 세션이 존재하면 해당 세션을 반환
		System.out.println("Filter - 세션이 존재하는지 확인"+session.getAttribute("loginMember"));
		
		if(session == null || session.getAttribute("loginMember") == null) {
			System.out.println("Filter - 세션 유효성 검사 완료 - 로그인 페이지로 이동");
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
		} else { // 그 외의 경우 필터 진행
			 chain.doFilter(request, response);
		}
		*/
	}

}
