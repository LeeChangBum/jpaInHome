package CBLEE.CBLEEspring.repository;

import CBLEE.CBLEEspring.Reopository.MemberRepository;
import CBLEE.CBLEEspring.Reopository.MemoryMemberRepository;
import CBLEE.CBLEEspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
    private final MemoryMemberRepository repository=new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("LeeChangBum");

        repository.save(member);
        Member result=repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member1.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member1.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }


}
