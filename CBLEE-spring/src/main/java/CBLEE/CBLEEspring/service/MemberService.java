package CBLEE.CBLEEspring.service;

import CBLEE.CBLEEspring.Reopository.MemberRepository;
import CBLEE.CBLEEspring.Reopository.MemoryMemberRepository;
import CBLEE.CBLEEspring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {//alt+enter로 바로 test 만들 수 있음

    private final MemberRepository memberRepository;//Java Tip) final은 원래는 무조건 초기화하여야 하지만 객체안의 변수라면 생성자나 static 블럭을 통한 초기화까지는 허용한다.


    public MemberService(MemberRepository memberRepository){//Java Tip) 인터페이스 타입 변수는 구현 객체타입을 가리킬 수 있다.(이걸로 다형성 실현가능하다!!)
        this.memberRepository=memberRepository;
    }


    public Long join(Member member){


            memberRepository.findByName(member.getName())//ctrl+alt+v하면 리턴하는 객체 만들어줌
                    .ifPresent(m -> {//ifPresent() -> Optional의 값이 null이 아니면이라는 의미
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
        memberRepository.save(member);
        return member.getId();
    }

    /*
    전체 회원 조회
    */
    public List<Member> findmembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
    }

}
