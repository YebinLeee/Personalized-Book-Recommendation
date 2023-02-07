package hello.hellospring.repository;

import hello.hellospring.domain.MemberInfo;

import java.util.List;
import java.util.Optional;

public interface MemberInfoRepository {
    MemberInfo save(MemberInfo info);
    Optional<MemberInfo> findById(Long id);
    Optional<MemberInfo> findByName(String name);
    List<MemberInfo> findAll();
}
