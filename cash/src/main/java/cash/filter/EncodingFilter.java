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
		
	}

}
