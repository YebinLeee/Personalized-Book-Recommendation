package bookrecommendation.book.service;

import bookrecommendation.book.domain.Member;
import bookrecommendation.book.repository.MemberRepository;
import bookrecommendation.book.service.Impl.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberServiceImpl memberService;
    @Autowired  MemberRepository memberRepository;

    /**
     * 회원 가입 테스트
     */
    @Test
    void 회원가입() { // 테스트 메서드 이름은 한글로 작성
        // Given
        Member member = new Member();
        member.setName("bookrecommendation");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원_예외(){
        // Given
        Member member1 = new Member();
        member1.setName("bookrecommendation");

        Member member2 = new Member();
        member2.setName("bookrecommendation");

        // When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}