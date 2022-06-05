package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    //private final ObjectProvider<MyLogger> myLogger;
    private final MyLogger myLogger;

    public void logic(String id){
        // Mylogger myLogger=myLoggerProvider.getObject();//같은 request면 같은 mylogger객체가 반환된다!!!
        myLogger.log("service id= "+id);
    }
}
