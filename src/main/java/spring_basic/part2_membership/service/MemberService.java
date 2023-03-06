package spring_basic.part2_membership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_basic.part2_membership.domain.Member;
import spring_basic.part2_membership.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

/*
@Service 없이 MemberController에서 MemberService 변수를 Constructer에 사용시 아래 오류 발생
Consider defining a bean of type 'spring_basic.part2_membership.service.MemberService' in your configuration.

@Component : 선언된 클래스를 스프링 컨테이너에 스프링 빈으로 등록한다. 단, 동일한 패키지 안엔서만(spring_basic.part2_membership.service)
스프링 빈은 기본적으로 유일하게 하나만 등록되고 공유된다.
@Service, @Repository, @Controller는 @Compenent를 포함한다.
 */
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //store과 sequence가 static(메모리에 선언됨) 이기에 다른 인스턴스더라도 같은 값을 공유가능
    //두 변수가 static이 아니게되면 오류가 발생한다. ==> 같은 인스턴스를 사용하도록 변경 필요
    @Autowired //MemberSerice를 스피링 컨테이너에서 등록하는 과정에서, MemberRepository의 구현체 MemoryMemberRepository 또한 등록한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //외부에서 값을 받는다
    }

    //ctrl + shift + alt + t ==> 리펙토링 가능
    //회원 가입
    public Long join(Member member){
        checkDuplicateName(member);     //중복 이름 확인
        memberRepository.save(member);
        return member.getId();

    }

    private void checkDuplicateName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 조회
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
