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
public class filter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		// 웹 요청일 경우에만 인코딩 실행
		if(request instanceof HttpServletRequest) {
			request.setCharacterEncoding("utf-8");
			
			System.out.println("utf-8 필터 적용 완료 : ");
			 
		}
		
		String uri = httpRequest.getRequestURI();
		
		if(uri.endsWith("/login") || uri.endsWith("/addMember")
				) { // 로그인 페이지 및 특정 조건의 세션 유효성의 경우 세션 검사를 건너띄고 개별적으로 처리
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = httpRequest.getSession(false); //세션을 새로 생성하지 않고 기존 세션을 불러옴
		
		if(session == null || session.getAttribute("loginMember") == null) {
			System.out.println("세션 유효성 검사 완료 - 로그인 페이지로 이동");
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
			return;
		} else { // 그 외의 경우 필터 진행
			 chain.doFilter(request, response);
		}
		
	}

}
