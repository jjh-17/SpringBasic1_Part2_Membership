package spring_basic.part2_membership;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic.part2_membership.repository.MemberRepository2;
import spring_basic.part2_membership.repository.MemoryMemberRepository2;
import spring_basic.part2_membership.service.MemberService2;


/*
[자바 코드로 스프링 빈 직접 등록]
상황에 따라 등록해야할 클래스가 변경될 경우, 설정 파일 수정만으로 스프링 빈 등록 가능
 */
@Configuration
public class SpringConfig {
    @Bean
    public MemberService2 memberService2() {
        return new MemberService2(memberRepository2());
    }

    @Bean
    public MemberRepository2 memberRepository2(){
        return new MemoryMemberRepository2();

    }
}
