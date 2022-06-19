package SpringMVC.servlet.web.frontController.v5.adapter;

import SpringMVC.servlet.web.frontController.ModelView;
import SpringMVC.servlet.web.frontController.MyView;
import SpringMVC.servlet.web.frontController.v4.ControllerV4;
import SpringMVC.servlet.web.frontController.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String,String > paramMap=createParammap(request);
        Map<String, Object> model=new HashMap<>();//추가
        String viewname = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewname);
        mv.setModel(model);

        return mv;
    }
    private Map<String, String> createParammap(HttpServletRequest request) {
        Map<String, String >paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName-> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

