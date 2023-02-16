package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return id
     */
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * id로 회원 조회
     * @param memberId
     * @return Member
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * RFID Number로 회원 조회
     * @param rfid
     * @return
     */
    public Optional<Member> validateByRfid(String rfid){
        return memberRepository.findByRfid(rfid);
    }

    /**
     * Barcode Number로 회원 조회
     * @param barcode
     * @return
     */
    public Optional<Member> validateByBarcode(String barcode){
        return memberRepository.findByBarcode(barcode);
    }
}
