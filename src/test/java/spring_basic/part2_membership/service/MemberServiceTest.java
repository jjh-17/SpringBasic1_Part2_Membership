package spring_basic.part2_membership.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring_basic.part2_membership.domain.Member;
import spring_basic.part2_membership.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        //MemberService 내 memberRepository와 선언한 memberRepository가 동일한 인스턴스를 사용하도록 코딩
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //테스트는 한글로 써도 상관 없음 ==> 차피 나 밖에 안볼건데 뭐
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("jjh");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원가입(){
        //given
        Member member = new Member();
        Member member2 = new Member();
        member.setName("jjh");
        member2.setName("jjh");

        //when
        // ctrl + alt + v : 변수 추출 ==> assertThrows애서 수행 시 IllegalStateException e 추출
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//람다식 수행 과정에서 IllegalStateException이 발동하였다면

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void 회원검색() {
    }

    @Test
    void findOne() {
    }
}