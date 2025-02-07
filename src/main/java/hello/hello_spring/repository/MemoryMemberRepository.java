package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

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
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //람다
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
} //내가 원하는 동작이 잘이루어지는가 확인하기위해 테스트케이스 작성함.
/* 이때 테스트케이스를 만들때 자바는 JUnit이라는 프레임워크로 테스트를 실행해서 main메소드로 테스트하거나 웹 애플리케이션의 컨트롤러를 통해
 테스트할때 발생하는 준비시 걸리는 시간, 반복 실행 어려움, 여러 테스트 통합실행 어려움 이라는 단점을 해결함 */

