package CBLEE.CBLEEspring.Reopository;

import CBLEE.CBLEEspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override//name이 아닌 다른 회사에서는 username이라고 할 수도 있다. 따라서 이런건 override해주어야함.
    Optional<Member> findByName(String name);
}
