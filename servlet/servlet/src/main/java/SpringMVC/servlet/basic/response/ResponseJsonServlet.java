package SpringMVC.servlet.basic.response;

import SpringMVC.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper=new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData=new HelloData();
        helloData.setAge(20);
        helloData.setUsername("Lee");

        //{"username":"Lee", "age":20}
        String messageBody=objectMapper.writeValueAsString(helloData);//이것도 알아서 객체의 프로퍼티:키 값으로 JSON데이터 형식으로 만들어줌
        response.getWriter().write(messageBody);
    }
}
