package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    // 데이터 베이스 연결 (dataSource 주입)

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // MemberService, MemberRepository 를 스프링 빈에 등록

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("datasource에서 데이터를 가져옵니다 : " + dataSource);
        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
