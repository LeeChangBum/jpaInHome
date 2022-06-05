package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan//@Component가 붙은 클래스를 스캔해서 스프링 빈에 등록함,@Configuration이 붙은 설정 정보도 자동으로 등록됨(안에 @Component 붙어있음)
        (
                basePackages = "hello.core",//이 패키지 하위만 스캔함
                excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)//Configuration 어노테이션이 붙은 class들을 제외하겠다는 의미
        )
public class AutoAppConfig {

}
