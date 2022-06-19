//WEB-INF에 있는 파일들은 외부에서 직접 호출할 수 없다. 반드시 controller를 거쳐야 한다!!!!
package SpringMVC.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//얘를 controller라고 생각해보자
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath="/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//controller에서 view로 넘어갈 때 사용
        dispatcher.forward(request, response);//servlet에서 jsp로 넘어감
        /*
        다른 서블릿이나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.(따라서 URL 안변하고 클라이언트는 이를 인지하지 못함)
         */
    }
}
