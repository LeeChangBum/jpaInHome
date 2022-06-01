package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;

//이걸 구현함으로써 DIP를 지킬 수 있다!!!
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // 어떤 역할에 어떤 구현체를 넣겠다는 것을 명시하기 위해 빼준다.
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService(){
        return new OrderServiceImp(memberRepository(), discountPolicy());
    }

    // 어떤 역할에 어떤 구현체를 넣겠다는 것을 명시하기 위해 빼준다.
    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
