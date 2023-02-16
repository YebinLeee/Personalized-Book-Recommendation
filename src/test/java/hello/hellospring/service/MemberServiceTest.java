package hello.hellospring.service;

import hello.hellospring.domain.Gender;
import hello.hellospring.domain.Member;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceTest {
   @Autowired MemberService memberService;

   @BeforeEach
   void beforeEach(){
       Member member = new Member();

       member.setName("springer");
       member.setPassword("1234");
       member.setAge(24);
       member.setGender(Gender.FEMALE);
       member.setRfid("1234");

       memberService.join(member);
   }
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


    @Test
    void 회원가입시_이름_없는_경우(){
        Member member = new Member();

        member.setAge(24);
        member.setGender(Gender.MALE);
        member.setPassword("1234");

        assertThrows(PersistenceException.class, ()-> memberService.join(member));
    }

    @Test
    void 회원_임시로그인_성공(){
        assertThat(memberService.validateByRfid("1234").get()).isInstanceOf(Member.class);
    }

    @Test
    void 회원_임시로그인_실페_RFID가다른경우(){
        assertThrows(NoSuchElementException.class, () -> memberService.validateByRfid("12345").get());
    }
}