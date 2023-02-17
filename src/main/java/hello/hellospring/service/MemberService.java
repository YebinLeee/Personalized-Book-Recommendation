package hello.hellospring.service;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(Member member);
    void validateDuplicateMember(Member member);
    List<Member> findMembers();
    Optional<Member> findOne(Long memberId);
    Optional<Member> validateByRfid(String rfid);
    Optional<Member> validateByBarcode(String barcode);
}
