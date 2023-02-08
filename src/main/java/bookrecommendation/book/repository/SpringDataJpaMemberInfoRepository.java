package bookrecommendation.book.repository;

import bookrecommendation.book.domain.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberInfoRepository extends JpaRepository<MemberInfo, Long>, MemberInfoRepository {
    @Override
    Optional<MemberInfo> findByName(String name);
}
