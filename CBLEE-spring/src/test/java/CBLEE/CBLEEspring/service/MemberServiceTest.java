package CBLEE.CBLEEspring.service;

import CBLEE.CBLEEspring.Reopository.MemberRepository;
import CBLEE.CBLEEspring.Reopository.MemoryMemberRepository;
import CBLEE.CBLEEspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest -> spring컨테이너까지 전부 실행시키면서 test를 한다.(하지만 없이 하면 순수 java에서 테스트를 하는 단위테스트를 진행하는데 후자가 더 좋긴하다.)
//@Transactional -> DB와 연동한 후에 진행하는 테스트로써 @Test가 끝나면 transaction을 rollback해준다!!
class MemberServiceTest {

    MemoryMemberRepository memberRepository;

    MemberService memberService;



    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();// 함수 안에서 초기화하면 괜찮은듯
        memberService=new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member=new Member();
        member.setName("hello");
        //when
        Long saveId= memberService.join(member);
        //then
        Member findMember=memberService.findOneMember(saveId).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        Member member2=new Member();

        member1.setName("spring");
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//(오른쪽 함수를 실행할건데 왼쪽 exception이 터지면 성공)
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e) {
        }*/

        //then
    }

    @Test
    void findmembers() {
    }

    @Test
    void findOneMember() {
    }
}