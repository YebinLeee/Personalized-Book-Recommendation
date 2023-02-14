package hello.hellospring.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearchedResult {

    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String isbn;
    private Long categoryId;
    private String categoryName;
    private String publisher;
    private Long priceSales;
    private String coverLink;
}
