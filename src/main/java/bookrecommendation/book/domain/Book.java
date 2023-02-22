package bookrecommendation.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "recommended_book")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recommendation_book_id")
    private int id;

    @Column(name = "book_name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "book_img_link")
    @JsonProperty(value = "img_url")
    private String imgLink;

    @Column(name = "book_link")
    @JsonProperty("url")
    private String bookLink;

    @ManyToOne()
    RecommendResult recommendResult;
}
