package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.MemberInfo;
import bookrecommendation.book.repository.MemberInfoRepository;
import bookrecommendation.book.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberInfoServiceImpl implements MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public MemberInfoServiceImpl(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    /**
     * 회원 가입
     *
     * @param info
     * @return id
     */
    public Long join(MemberInfo info) {
        validateDuplicateMember(info);
        memberInfoRepository.save(info);
        return info.getId();
    }

    public void validateDuplicateMember(MemberInfo info) {
        memberInfoRepository.findByName(info.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<MemberInfo> findMemberInfos() {
        return memberInfoRepository.findAll();
    }

    /**
     * id로 회원 조회
     *
     * @param memberId
     * @return Member
     */
    public Optional<MemberInfo> findOne(Long memberId) {
        return memberInfoRepository.findById(memberId);
    }
}
