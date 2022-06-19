package SpringMVC.servlet.web.frontController.v3;

import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.MyView;

import SpringMVC.servlet.web.frontController.v3.ControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberListControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();//받은 URI를 저장한다.
        ControllerV3 controller=controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap

        Map<String, String> paramMap=createParammap(request);
        ModelView mv = controller.process(paramMap);//논리적 이름(new-form, save...)과 model을 가진 mv를 반환

        //new-form
        String viewname = mv.getViewname();//논리적이름을 꺼냄
        MyView myView = viewResolver(viewname);//물리적이름(정확한 URL)을 가진 myView생성

        myView.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewname) {
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }

    private Map<String, String> createParammap(HttpServletRequest request) {
        Map<String, String >paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
