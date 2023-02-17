package hello.hellospring.service.Impl;

import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.domain.Gender;
import hello.hellospring.service.BookSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookSearchServiceImplTest {

    @Autowired
    BookSearchService bookSearchService;

    @Test
    void searchBooksByQuery() {
    }

    @Test
    void searchBooksByCategory() {
    }

    @Test
    void searchBooksByAgeandGender() {
        int age = 24;
        Gender gender = Gender.FEMALE;

        List<BookSearchedResult> results = bookSearchService.searchBooksByAgeandGender(age, gender);
        for (BookSearchedResult result : results) {
            System.out.println("result.getTitle() = " + result.getTitle());
        }
    }

    @Test
    void getAladinBookResults() {
    }
}