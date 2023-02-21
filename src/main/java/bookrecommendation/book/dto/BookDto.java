package bookrecommendation.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String isbn;
    private String categoryId;
    private String categoryName;
    private String publisher;
    private String coverLink;
}
