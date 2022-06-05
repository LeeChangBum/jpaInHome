package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//configuration으로 등록한 AppConfig를 사용해서 컨테이너를 구성하라.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 저장된 bean은 bean으로 등록하는 함수이름으로 등록된다.(key: 함수이름, value:return 값)
        // 따라서 등록한 함수이름 (memberService) 그리고 등록한 객체의 종류(MemberService.class)로 등록이 된 것이다.
        Member member = new Member(1L, "mamberA", Grade.VIP);
        memberService.join(member);
    }
}
