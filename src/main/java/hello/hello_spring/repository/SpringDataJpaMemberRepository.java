package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository /*둘다 인터페이스*/{

    //findByName의 이름만으로
    //select m from Member m where m.name = ? 의 간단한 쿼리를 짠다. JPQL로 번역되어서 지 스스로 한다고함, 그래서 인터페이스 이름만으로도 개발이 끝난다고함.
    @Override
    Optional<Member> findByName(String name);
}
