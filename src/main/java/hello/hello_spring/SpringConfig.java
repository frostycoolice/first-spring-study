package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //자바 코드로 직접 스프링 빈 들록하기
// 컴포넌트 스캔 방식을 사용하든 자바코드로 직접 스프링 빈 등록하든 컨트롤러는 Controller에 어노테이션 @Controller를 해주어야함.
// 그러나 Service 부분에 @Service, Repository 부분에 @Repository를 코딩해주면 된다.

public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    /*
    편리성은 확실히 컴포넌트 스캔이 더 좋음
     */
}
