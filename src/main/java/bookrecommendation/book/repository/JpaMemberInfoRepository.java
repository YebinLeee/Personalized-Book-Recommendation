package bookrecommendation.book.repository;

import bookrecommendation.book.domain.MemberInfo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaMemberInfoRepository implements MemberInfoRepository {
    private final EntityManager em;

    public JpaMemberInfoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MemberInfo save(MemberInfo info) {
        em.persist(info);
        return info;
    }

    public Optional<MemberInfo> findById(Long id) {
        MemberInfo info = em.find(MemberInfo.class, id);
        return Optional.ofNullable(info);
    }

    public List<MemberInfo> findAll() {
        return em.createQuery("select m from MemberInfo m", MemberInfo.class)
                .getResultList();
    }

    public Optional<MemberInfo> findByName(String name) {
        List<MemberInfo> result = em.createQuery("select m from MemberInfo m where m.name = :name", MemberInfo.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
