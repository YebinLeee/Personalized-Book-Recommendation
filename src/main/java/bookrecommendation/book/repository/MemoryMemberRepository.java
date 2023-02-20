package bookrecommendation.book.repository;

import bookrecommendation.book.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 0, 1, 2 key 값을 차례 대로 생성할 static 변수


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findByRfid(String rfid) {
        return store.values().stream()
                .filter(member -> member.getRfid().equals(rfid))
                .findAny();
    }

    @Override
    public Optional<Member> findByBarcode(String barcode) {
        return store.values().stream()
                .filter(member -> member.getBarcode().equals(barcode))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}