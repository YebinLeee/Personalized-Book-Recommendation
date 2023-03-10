package bookrecommendation.book.service;

import bookrecommendation.book.domain.MemberInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberInfoService {
    Long join(MemberInfo info);
    void validateDuplicateMember(MemberInfo info);
    List<MemberInfo> findMemberInfos();
    Optional<MemberInfo> findOne(Long memberId);
}
