package cash.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootListener implements ServletContextListener {


    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("실행확인: ServletContextListener.contextInitialized()"); // tomcat 부팅시 실행
        
        
        // 서블릿에서 ServletContext가 application 객체
        ServletContext application = sce.getServletContext(); // 이벤트의 주체
        application.setAttribute("currentCounter", 0);
        
        
        try {
			Class.forName("org.mariadb.jdbc.Driver"); // 메모리에 드라이버를 한번만 올린다 -> 시작 시기는 톰캣 실행 시
		} catch (ClassNotFoundException e) {
			System.out.println("MariaDB 드라이버 로딩 실패");
			e.printStackTrace();
		} 
        
        System.out.println("MariaDB 드라이버 로딩 성공");
    }
	
}
