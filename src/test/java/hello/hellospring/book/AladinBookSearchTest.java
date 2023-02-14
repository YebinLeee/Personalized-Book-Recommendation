package hello.hellospring.book;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.service.BookSearchService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

@Transactional
public class AladinBookSearchTest {
    @Test
    void 자바_쿼리로_책검색하기h(){
        BookSearchService bookSearchService = new BookSearchService();
        BookSearchQuery query = new BookSearchQuery();
        query.setQuery("java");

        List<BookSearchedResult> bookSearchedResults = bookSearchService.searchBooks(query);
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
