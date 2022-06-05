package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{//옵션처리를 위한 configuration
        @Autowired(required = false)//만약 parameter가 빈에 등록되지 않았으면 아예 실행을 안함
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired//만약 parameter가 빈에 등록되지 않았으면 noBean2에 null을 넣어줌
        public void setNoBean2(@Nullable  Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired//만약 parameter가 빈에 등록되지 않았으면 Optional.empty
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
