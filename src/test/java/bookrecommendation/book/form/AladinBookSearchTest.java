package bookrecommendation.book.form;

import bookrecommendation.book.dto.BookSearchQuery;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.service.Impl.BookSearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AladinBookSearchTest {
    @Autowired private BookSearchServiceImpl bookSearchService;

    @Test
    void 자바_쿼리로_책검색하기(){
        BookSearchQuery query = new BookSearchQuery();
        query.setQuery("java");

        List<BookDto> bookDtos = bookSearchService.searchBooksByQuery(query);
        for (BookDto bookDto : bookDtos) {
            System.out.println("title = " + bookDto.getTitle());
            System.out.println("author = " + bookDto.getAuthor());
            System.out.println("isbn = " + bookDto.getIsbn());
            System.out.println("publisher = " + bookDto.getPublisher());
            System.out.println("category name = " + bookDto.getCategoryName());
            System.out.println("pub date = " + bookDto.getPubDate());
            System.out.println("\n\n");
        }
    }
}
