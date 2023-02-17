package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberInfoRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberInfoRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.BookSearchService;
import hello.hellospring.service.Impl.BookSearchServiceImpl;
import hello.hellospring.service.Impl.MemberInfoServiceImpl;
import hello.hellospring.service.Impl.MemberServiceImpl;
import hello.hellospring.service.MemberInfoService;
import hello.hellospring.service.MemberService;
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
