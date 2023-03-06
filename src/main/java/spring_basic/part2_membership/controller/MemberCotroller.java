package spring_basic.part2_membership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import spring_basic.part2_membership.service.MemberService;
import spring_basic.part2_membership.service.MemberService2;

@Controller //Spring 생성 시 아래 클래스를 자동으로 생성하여 관리한다. ==> 의존성
public class MemberCotroller {
    /*
    private final MemberService memberService = new MemberSerive();
    new를 이용해 객체를 생성 시, MemberController 뿐만 아니라 다른 Controller에서도 사용가능

    <field 주입>
    @Autowired private MemberService memberService;
    선언된 변수를 이후 수정할 방도가 없다

    <setter 주입>
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    public으로 선언을 해야하기 때문에 은닉성이 떨어진다

    <생성자 주입> : 권장
    첫 생성 이후엔 접근을 할 수가 없음 ==> 은닉성이 좋음

     */
    private final MemberService memberService;
    private final MemberService2 memberService2;

    @Autowired //스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌 ==> 의존성 주입(D.I)
    public MemberCotroller(MemberService memberService, MemberService2 memberService2) {
        this.memberService = memberService;
        this.memberService2 = memberService2;
    }
}
