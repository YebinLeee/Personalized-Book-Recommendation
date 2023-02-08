package bookrecommendation.book.repository;

import bookrecommendation.book.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findByRfid(String rfid);

    @Override
    Optional<Member> findByBarcode(String barcode);
}
