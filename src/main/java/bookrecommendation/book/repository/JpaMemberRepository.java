package bookrecommendation.book.repository;

import bookrecommendation.book.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    public Optional<Member> findByRfid(String rfid) {
        List<Member> result = em.createQuery("select m from Member m where m.rfid = :rfid", Member.class)
                .setParameter("rfid", rfid)
                .getResultList();
        return result.stream().findAny();
    }

    public Optional<Member> findByBarcode(String barcode) {
        List<Member> result = em.createQuery("select m from Member m where m.barcode = :barcode", Member.class)
                .setParameter("barcode", barcode)
                .getResultList();
        return result.stream().findAny();
    }
}
