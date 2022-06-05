package CBLEE.CBLEEspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP 메소드를 호출 할때마다 중간에서 intercept해서 걸림(즉 공통 관심 사항을 구현 가능)
public class TimeTraceAop {

    @Around("execution(* CBLEE.CBLEEspring..*(..))")//해당 패키지 하위 모든 class에 적용하겠다는 의미
    public  Object execute(ProceedingJoinPoint joinPoint) throws Throwable{ //joinPoint
        long start=System.currentTimeMillis();
        System.out.println("Start:"+joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish= System.currentTimeMillis();
            long timeMs= finish - start;
            System.out.println("End:"+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
