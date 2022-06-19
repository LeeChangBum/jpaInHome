package SpringMVC.servlet.web.frontController.v5;

import SpringMVC.servlet.web.frontController.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface MyHandlerAdapter {

    boolean supports(Object handler);//자신이 해당 controller를 지원할 수 있는지 확인

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)throws ServletException, IOException;
}
