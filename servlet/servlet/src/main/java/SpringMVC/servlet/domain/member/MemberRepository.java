package SpringMVC.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용고려
 */
public class MemberRepository {
    private static Map<Long,Member> store=new ConcurrentHashMap<>();
    private static long sequence=0L;

    private static final MemberRepository instance= new MemberRepository();

    /*
    싱글톤을 위하여 생성자를 못만들게 하고 함수를 통해서만 접근할 수 있게함
     */
    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
