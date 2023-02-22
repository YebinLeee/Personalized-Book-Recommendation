package bookrecommendation.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "member_info_tbl")
public class MemberInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_info_id")
    private Long id;

    @Column(name = "feeling1_code")
    private String feelingCode1;

    @Column(name = "feeling2_code")
    private String feelingCode2;

    @Column(name = "feeling3_code")
    private String feelingCode3;

    @Column(name = "interest_code")
    private String interest;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "result_id")
    private RecommendResult result;
}
