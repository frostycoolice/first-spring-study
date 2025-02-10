package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //자바 코드로 직접 스프링 빈 들록하기
// 컴포넌트 스캔 방식을 사용하든 자바코드로 직접 스프링 빈 등록하든 컨트롤러는 Controller에 어노테이션 @Controller를 해주어야함.
// 그러나 Service 부분에 @Service, Repository 부분에 @Repository를 코딩해주면 된다.

public class SpringConfig {

//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    } // 20년전 과거, JDBC 방식으로 했던것.

//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }//jpa활용할때 썼던것.

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean //TimeTraceAop 스프링 빈에 등록하는법
//    // 1. 직접 TimeTraceAop 클래스에 들어가서 위에 @Component하기
//    // 2. 이렇게 @Bean으로 등록하기 <- 정형화되는 클래스들은 Component 방식이 낫지만 이러한 정형화되지않은 클래스들은 직접 @Bean등록이 낫다.
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {  //전체 주석때는 이걸 스프링 데이터 JPA를 활용한 것임
        // return new MemoryMemberRepository();
        // 스프링 컨테이너가 다형성을 이용하여 구현체를 바꿔 낀 것 (스프링이 좋은 이유)
        // 개방 폐쇄 원칙 (OCP Open-Closed Principle) SOLID원칙
        //return new JdbcMemberRepository(dataSource); // 20년전 과거 데이터베이스 연결 방식으로한 버전?
        //return new JdbcTemplateMemberRepository(dataSource); //JDBC 데이터베이스 연결방식으로한 버전
        //return new JpaMemberRepository(em); //jpa 활용


    /*
    편리성은 확실히 컴포넌트 스캔이 더 좋음
     */
    //}
}
