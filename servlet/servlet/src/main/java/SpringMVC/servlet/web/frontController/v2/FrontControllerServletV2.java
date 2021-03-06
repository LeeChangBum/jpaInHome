package SpringMVC.servlet.web.frontController.v2;

import SpringMVC.servlet.web.frontController.MyView;
import SpringMVC.servlet.web.frontController.v2.controller.MemberFormControllerV2;
import SpringMVC.servlet.web.frontController.v2.controller.MemberListControllerV2;
import SpringMVC.servlet.web.frontController.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2(){
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI=request.getRequestURI();//받은 URI를 저장한다.
        ControllerV2 controller=controllerMap.get(requestURI);
        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView myview=controller.process(request, response);
        myview.render(request, response);
    }
}