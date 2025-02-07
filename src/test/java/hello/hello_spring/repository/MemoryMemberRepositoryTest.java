package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // junit을 사용해서 테스트케이스하기

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각각 테스트케이스가 끝날때마다 저장소의 값을 비워준다. (공용데이터를 지움)
    public void afterEach(){
        repository.clearStore();
    }

    @Test  //Test org.junit.jupiter.api
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        // member와 result가 같은지 확인해주는 메소드, 같으면 그냥 초록색 확인, 다르면 빨간색 오류메시지 발생
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    /*테스트는 순서대로 동작하지않음, 디버거가 자기가 알아서 실행함. 그래서 각각 테스트메소드에서 완전히 성공하지 않을 수 있음
    예를들어서 지금 이 주석들 다는 동안에 3개를 동시에 돌렸을때 findAll에서 이미 객체로 저장이 되어버려서 findByName 메소드에서 테스트케이스를
    진행할때 오류가 발생함
     */
}
