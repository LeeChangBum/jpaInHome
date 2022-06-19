package SpringMVC.servlet.web.frontController.v5;

import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.MyView;
import SpringMVC.servlet.web.frontController.v3.ControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberListControllerV3;
import SpringMVC.servlet.web.frontController.v3.controller.MemberSaveControllerV3;
import SpringMVC.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import SpringMVC.servlet.web.frontController.v4.controller.MemberListControllerV4;
import SpringMVC.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import SpringMVC.servlet.web.frontController.v5.adapter.ControllerV3HandlerAdapter;
import SpringMVC.servlet.web.frontController.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlermappingmap=new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters=new ArrayList<>();

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }


    /*
    handlermappingmap을 DI식으로 만들어 보자
     */
    private void initHandlerMappingMap() {
        handlermappingmap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlermappingmap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlermappingmap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlermappingmap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlermappingmap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlermappingmap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if(handler==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter=getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        //new-form
        String viewname = mv.getViewname();//논리적이름을 꺼냄
        MyView myView = viewResolver(viewname);//물리적이름(정확한 URL)을 가진 myView생성

        myView.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {//handlerAdapters.iter쓰면 for문으로 알아서 바꾸어줌
            if(handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI= request.getRequestURI();//받은 URI를 저장한다.
        return handlermappingmap.get(requestURI);
    }

    private MyView viewResolver(String viewname) {
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }

}
