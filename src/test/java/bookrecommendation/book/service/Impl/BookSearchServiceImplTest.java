package bookrecommendation.book.service.Impl;

import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.service.BookSearchService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
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

        List<BookDto> results = bookSearchService.searchBooksByAgeandGender(age, gender);
        for (BookDto result : results) {
            System.out.println("result.getTitle() = " + result.getTitle());
        }
    }

    @Test
    void searchBooksByFeeling(){
        String feeling = "기쁨";

        List<BookDto> results = bookSearchService.searchBooksByFeeling(feeling);
        for (BookDto result : results) {
            System.out.println("result = " + result.getTitle());
        }
    }

    @Test
    void getAladinBookResults() {
    }
}