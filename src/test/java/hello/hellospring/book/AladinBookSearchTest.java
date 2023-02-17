package hello.hellospring.book;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.service.Impl.BookSearchServiceImpl;
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

        List<BookSearchedResult> bookSearchedResults = bookSearchService.searchBooksByQuery(query);
        for (BookSearchedResult bookSearchedResult : bookSearchedResults) {
            System.out.println("title = " + bookSearchedResult.getTitle());
            System.out.println("author = " + bookSearchedResult.getAuthor());
            System.out.println("isbn = " + bookSearchedResult.getIsbn());
            System.out.println("publisher = " + bookSearchedResult.getPublisher());
            System.out.println("category name = " + bookSearchedResult.getCategoryName());
            System.out.println("pub date = " + bookSearchedResult.getPubDate());
            System.out.println("\n\n");
        }
    }
}
