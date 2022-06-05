package CBLEE.CBLEEspring.Reopository;

import CBLEE.CBLEEspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class MemoryMemberRepository implements MemberRepository{ //ctrl+i로 override 함수 자동완성 가능

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static Long sequence=0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //.values()에서 collection 객체 return 그리고 .stream()으로 stream 형식으로 만듦 이후는 구글 검색 ㄱ
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
