package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberInfoRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberInfoRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.BookSearchService;
import hello.hellospring.service.MemberInfoService;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberInfoService memberInfoService(){
        return new MemberInfoService(memberInfoRepository());
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    @Bean
    public MemberInfoRepository memberInfoRepository(){
        return new JpaMemberInfoRepository(em);
    }

    @Bean
    public BookSearchService bookSearchService(){
        return new BookSearchService();
    }

    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }

}
