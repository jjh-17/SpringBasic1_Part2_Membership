package spring_basic.part2_membership.repository;


import spring_basic.part2_membership.domain.Member;
import java.util.List;
import java.util.Optional;

//회원 정보 저장소
public interface MemberRepository2 {
    Member save(Member member);                 //멤버 정보 저장
    Optional<Member> findById(Long id);         //ID로 멤버 검색
    Optional<Member> findByName(String name);   //이름으로 멤버 검색
    List<Member> findAll();                     //모든 멤버 정보 출력
}
