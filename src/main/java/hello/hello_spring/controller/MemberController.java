package hello.hello_spring.controller;


import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 컨트롤러와 서비스 부분을 연결함.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
