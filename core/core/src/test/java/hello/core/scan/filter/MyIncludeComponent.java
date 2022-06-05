package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//class라는 의미
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
