package bookrecommendation.book;

import bookrecommendation.book.aop.TimeTraceAop;
import bookrecommendation.book.repository.JpaMemberInfoRepository;
import bookrecommendation.book.repository.JpaMemberRepository;
import bookrecommendation.book.repository.MemberInfoRepository;
import bookrecommendation.book.repository.MemberRepository;
import bookrecommendation.book.service.BookSearchService;
import bookrecommendation.book.service.Impl.BookSearchServiceImpl;
import bookrecommendation.book.service.Impl.MemberInfoServiceImpl;
import bookrecommendation.book.service.Impl.MemberServiceImpl;
import bookrecommendation.book.service.MemberInfoService;
import bookrecommendation.book.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class SpringConfig {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    public MemberInfoService memberInfoService(){
        return new MemberInfoServiceImpl(memberInfoRepository());
    }

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    public MemberInfoRepository memberInfoRepository(){
        return new JpaMemberInfoRepository(em);
    }

    public BookSearchService bookSearchService(){
        return new BookSearchServiceImpl();
    }

    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

}
