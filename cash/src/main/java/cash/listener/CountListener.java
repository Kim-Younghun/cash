package cash.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cash.service.CounterService;

@WebListener
public class CountListener implements HttpSessionListener {
	// 서비스 호출
	private CounterService counterService;

    public void sessionCreated(HttpSessionEvent se)  { 
    	
    	System.out.println(se.getSession().getId() + "의 새로운 세션이 생성되었습니다.");
    	// 현재 접속자 수 +1 -> application.attribute
    	// se -> Session -> 어플리케이션을 가져온다. 
    	ServletContext application = se.getSession().getServletContext(); 
    	int currentCounter = (Integer)application.getAttribute("currentCounter"); // 형변환 Object -> integer
    	application.setAttribute("currentCounter", currentCounter+1); // 세션에 저장된 currentCounter를 1증가 시킨다.
    	
    	System.out.println(currentCounter + "세션이 생성된 메서드 실행 : 현재 접속자 수");
    	
    	// 오늘 접속자 수 +1 -> DB
    	this.counterService = new CounterService();
    	int counter = counterService.getCounter(); // 오늘 카운트 수
    	if(counter == 0) {
    		counterService.addCounter();
    	} else {
    		counterService.modifyCounter();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println(se.getSession().getId() + " 세션이 소멸되었습니다.");
    	// 현재 접속자 수 -1
    	ServletContext application = se.getSession().getServletContext(); 
    	int currentCounter = (Integer)application.getAttribute("currentCounter"); // 형변환 Object -> integer
    	application.setAttribute("currentCounter", currentCounter-1); // 세션에 저장된 currentCounter를 1감소 시킨다.
    	
    	System.out.println(currentCounter + "세션이 제거된 메서드 실행 : 현재 접속자 수");
    }
}
