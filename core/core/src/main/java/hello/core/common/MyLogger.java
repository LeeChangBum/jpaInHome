package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)//HTTP 요청을 받고 controller에서 request하면 생기고 나갈 때까지 유지되는 스코프
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"] "+"["+message+"]");
    }

    @PostConstruct
    public void init(){
        uuid= UUID.randomUUID().toString();//세계에서 하나뿐인 number
        System.out.println("["+uuid+"] request scope  bean create:"+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope  bean close:"+this);
    }
}
