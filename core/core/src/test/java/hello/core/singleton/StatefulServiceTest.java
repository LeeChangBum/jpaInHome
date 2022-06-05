package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1=ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2=ac.getBean("statefulService", StatefulService.class);

        //ThreadA: 사용자A
        int userPriceA=statefulService1.order("userA",10000);
        //ThreadB: 사용자B
        int userPriceB=statefulService1.order("userB",20000);

    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}