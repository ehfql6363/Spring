package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { //함수 이름을 가능한 비즈니스 용어 사용

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름의 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member); //중복 회원 검증
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {//m을 매개변수로함. m이 존재하면 throw
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //Optional객체라서 가능.
        //memberRepository를 findByName으로 Optional객체로 가져옴
        //-> 가져온 객체가 Optional이기에 ifPresent로 객체가 존재 유무 판단.
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * id로 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
