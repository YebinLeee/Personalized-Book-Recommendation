package hello.hellospring.service;

import hello.hellospring.domain.Gender;
import hello.hellospring.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceTest {
   @Autowired MemberService memberService;

    /**
     * 회원 가입 테스트
     */
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        member.setPassword("0000");
        member.setAge(20);
        member.setRfid("E00401082F29A99D");
        member.setBarcode("EM0000069110");
        member.setGender(Gender.MALE);

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        assertThat(member.getBarcode()).isEqualTo(findMember.getBarcode());

        System.out.println("findMember = " + findMember.getId() + " | " + findMember.getRfid() + " | " + findMember.getBarcode());
        System.out.println("member = " + member.getId() + " | " + member.getRfid() + " | " + member.getBarcode());
    }

    @Test
    void 중복회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    /**
     * 전체 회원 반환 하기 테스트
     */
    @Test
    void findMembers() {
    }

    /**
     * id로 회원 찾기 테스트
     */
    @Test
    void findOne() {
    }
}