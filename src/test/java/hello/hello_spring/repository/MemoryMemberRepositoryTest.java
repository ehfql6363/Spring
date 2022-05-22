package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    //Test케이스는 순서없이 실행됨.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //기존 repository지우기
    @AfterEach
    public void afterEach() { //각 test가 끝날 때마다 실행됨
        repository.clearStore();
    }

    @Test //메소드를 실행할 수 있게 해줌
    public void save(){
        Member member = new Member();
        member.setName("Kim Dongyeol");

        repository.save(member);

        //member의 id를 가지고 해당 id의 repository의 Optional객체에서 Member를 불러옴.
        Member result = repository.findById(member.getId()).get();
//        Optional<Member> op = repository.findById(member.getId());
//        Member result = op.get();
        //Optional wrapper 클래스에서 Member클래스를 가져옴 = result

        //두 객체가 같은지 검사 - JUnit
        Assertions.assertEquals(member, result); //(비교하는 값 - 기댓 값, 실제 값)

        //assertJ
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
//        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
