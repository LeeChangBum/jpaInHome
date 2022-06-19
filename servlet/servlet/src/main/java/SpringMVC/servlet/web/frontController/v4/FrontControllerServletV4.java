package SpringMVC.servlet.web.frontController.v4;

import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.MyView;
import SpringMVC.servlet.web.frontController.v3.ControllerV3;

import SpringMVC.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import SpringMVC.servlet.web.frontController.v4.controller.MemberListControllerV4;
import SpringMVC.servlet.web.frontController.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();//받은 URI를 저장한다.
        ControllerV4 controller=controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap

        Map<String, String> paramMap=createParammap(request);
        Map<String, Object> model=new HashMap<>();//추가
        String viewname = controller.process(paramMap, model);

        MyView myView = viewResolver(viewname);//물리적이름(정확한 URL)을 가진 myView생성

        myView.render(model, request, response);
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
