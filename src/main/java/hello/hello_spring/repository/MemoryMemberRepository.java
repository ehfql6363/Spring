package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //id를 key, Member를 value
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 는 자동으로 설정
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //store의 value(= member객체)를 루프로 돌리면서
                .filter(member -> member.getName().equals(name)) //member의 이름(getName()으로 찾음)이 name인자와 같은지 확인.
                .findAny(); // 그 중에서 찾은 이름 하나라도 반환(Null도 반환 가능)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 value들(= member)
    }

    public void clearStore(){
        store.clear();
    }
}
