package SpringMVC.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream=request.getInputStream();//Http 메세지 바디내용을 바이트코드로 받음
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//바이트코드를 문자로 변환, 이떄 인코딩방식을 꼭 알려주어야 한다.(보통 UTF_8사용)

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
