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

@WebFilter("/*")
public class SessionFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// httpRequest의 객체를 생성
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
        HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();
		
		/*
		if(uri.contains("/login") || uri.contains("/addMember")) { // 로그인 된 상태이므로 캐시북 페이지로 리디렉션 하는 경우
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("로그인시 리디렉션 필터 실행 : 세션 값 ->"+session.getAttribute("loginMember"));
			if(session.getAttribute("loginMember") != null) { // 로그인이면
				System.out.println("로그인 된 상태이므로 캐시북 페이지로 리디렉션 ");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/cashbook");
				return;
			}
		}
		
		if(uri.contains("/addCashbook") || uri.contains("/calendar")
				|| uri.contains("/cashbook")
				|| uri.contains("/cashbookListByTag")
				|| uri.contains("/logout")
				|| uri.contains("/memberOne")
				|| uri.contains("/modifyMember")
				|| uri.contains("/removeMember")) { // 비로그인 된 상태이므로 로그인 페이지로 리디렉션 하는 경우
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("비로그인시 리디렉션 필터 실행 : 세션 값 ->"+session.getAttribute("loginMember"));
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		*/
		
		if(uri.contains("/addMember")) { // 회원가입 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("addMember session 필터 실행 ");
			if(session.getAttribute("loginMember") != null) { // 로그인이면
				System.out.println("로그인 된 상태이므로 캐시북 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/cashbook");
				return;
			}
		}
		
		
		if(uri.contains("/calendar")) { // 달력 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("calendar session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/cashbook")) { // 가계부 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("cashbook session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/cashbookListByTag")) { // 태그별 리스트 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("cashbookListByTag session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/logout")) { // 태그별 리스트 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("logout session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/memberOne")) { // 회원상세 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("memberOne session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/modifyMember")) { // 회원정보수정 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("modifyMember session 필터 실행 ");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		if(uri.contains("/removeMember")) { // 회원삭제 페이지의 경우 세션 유효성 검사 처리
			
			// session 인증 검사 코드
			HttpSession session = httpRequest.getSession();
			System.out.println("removeMember session 필터 실행 ");
			System.out.println(session+"세션 값 확인");
			if(session.getAttribute("loginMember") == null) { // 비로그인이면
				System.out.println("비로그인 된 상태이므로 로그인 페이지로 리디렉션");
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
				return;
			}
		}
		
		
		request = httpRequest;
		response = httpResponse;
		
		chain.doFilter(request, response);
		
	}
}
