package hello.hellospring.service;

import hello.hellospring.domain.MemberInfo;

import java.util.List;
import java.util.Optional;

public interface MemberInfoService {
    Long join(MemberInfo info);
    void validateDuplicateMember(MemberInfo info);
    List<MemberInfo> findMemberInfos();
    Optional<MemberInfo> findOne(Long memberId);
}
