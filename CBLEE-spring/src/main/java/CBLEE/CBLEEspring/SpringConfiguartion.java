package CBLEE.CBLEEspring;

import CBLEE.CBLEEspring.Reopository.SpringDataJpaMemberRepository;
import CBLEE.CBLEEspring.aop.TimeTraceAop;
import CBLEE.CBLEEspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguartion {

    private final SpringDataJpaMemberRepository memberRepository;//JpaRepository를 상속받을 시 spring에서 알아서 객체를 빈에 등록해준다.

    @Autowired
    public SpringConfiguartion(SpringDataJpaMemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    @Bean//service객체를 bean에 등록할 때 아래 함수를 실행하는 듯함
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean //AOP도 여기 적어주는게 좋음
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
