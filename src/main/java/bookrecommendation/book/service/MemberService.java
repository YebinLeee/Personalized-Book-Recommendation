package bookrecommendation.book.service;

import bookrecommendation.book.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
    Long join(Member member);
    void validateDuplicateMember(Member member);
    List<Member> findMembers();
    Optional<Member> findOne(Long memberId);
    Optional<Member> validateByRfid(String rfid);
    Optional<Member> validateByBarcode(String barcode);
}
