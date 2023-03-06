package spring_basic.part2_membership.repository;


import spring_basic.part2_membership.domain.Member;
import java.util.*;

public class MemoryMemberRepository2 implements MemberRepository2{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //Optional.ofNullable : null도 return 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()                          //store 루프를 돌면서
                .filter(member->member.getName().equals(name))  //member에 대하여 member에 저장된 name과 인자로 받은 name이 같다면
                .findAny();                                     //하나라도 찾았다면 이를 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        sequence = 0L;
    }
}
