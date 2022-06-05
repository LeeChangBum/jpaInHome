package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import java.lang.annotation.Annotation;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1=ac.getBean(ClientBean.class);
        int count1=clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2=ac.getBean(ClientBean.class);
        int count2=clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean;//생성시점에 주입 -> 이후로는 같은 prototypeBean을 사용한다.

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;//이 정도는 자동으로 빈으로 등록해줌

        //@Autowired
        //public ClientBean(PrototypeBean prototypeBean){
        //    this.prototypeBean=prototypeBean;
        //}

        public int logic(){
            PrototypeBean prototypeBean= prototypeBeanProvider.get();//이때 비로소 컨테이너에 prototypeBean을 달라고 요청함.
            prototypeBean.addCount();
            int count= prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")//프로토타입 bean이라는 의미
    static class PrototypeBean{

        private int count=0;

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init="+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("어차피 호출 안됨");
        }
    }
}
