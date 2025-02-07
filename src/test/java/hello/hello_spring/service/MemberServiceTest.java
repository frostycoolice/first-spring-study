package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    /* 테스트를 실행할때마다 생성해주는데 이렇게 한 이유는 원래 방식으로
    MemberService memberService = new MemberService();
     MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
     이렇게 하게되면 테스트할때마다 이름은 같아보이지만 사실상 다른 객체로 테스트를 하기 때문에 안좋을 영향을 끼칠 수 있다.
     그것을 방지하기위해
     private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
       }
       MemberService 클래스에서 요런식으로 코드를 다시 만들어주어서 같은 객체로 테스트할 수 있게 만드는 것이다.
       이런건 처음에  MemoryMemberRepository 클래스에서 static이라는 명령어를 배치하지않으면 위험도가 크게 올라가기때문에 이 방법을 사용하여 테스트를 수행하는 것이 좋다.
        이것을 dependency injection (DI) 이라고한다.
     */
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);

    }

    @AfterEach // 각각 테스트케이스가 끝날때마다 저장소의 값을 비워준다. (공용데이터를 지움)
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }


    @Test
    void 회원가입() {// 테스트 코드에서는 한글로 해도됨
        //given given when then 문법
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findone(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");





        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findone() {
    }
}