package bookrecommendation.book.repository;

import bookrecommendation.book.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findByRfid(String rfid);
    Optional<Member> findByBarcode(String barcode);
    List<Member> findAll();
}
