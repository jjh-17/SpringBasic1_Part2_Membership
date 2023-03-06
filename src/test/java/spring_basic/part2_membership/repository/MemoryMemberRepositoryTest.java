package spring_basic.part2_membership.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import spring_basic.part2_membership.domain.Member;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    //클래스에서 실행을 하면 전체 테스트 가능
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 완료 시 수행되는 메서드 ==> 데이터 초기화
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member m1 = new Member();
        m1.setName("spring1");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repository.save(m2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(m1);

    }
    @Test
    public void findAll(){
        Member m1 = new Member();
        m1.setName("spring1");
        repository.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repository.save(m2);

        List<Member> result = repository.findAll();
        List<Member> expected = Arrays.asList(m1, m2);

        assertThat(result.size()).isEqualTo(2);
        assertThat(expected).isEqualTo(result);
    }
}
