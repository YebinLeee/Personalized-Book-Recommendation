package bookrecommendation.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recommendation_result")
public class RecommendResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private int id;

    @Column(name = "recommended_at")
    @CreatedDate
    private LocalDateTime recommendedAt;

    @OneToOne(mappedBy = "result")
    private MemberInfo memberInfo;

    @OneToMany
    private List<Book> bookList = new ArrayList<>();

    public RecommendResult(){
        this.recommendedAt = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
