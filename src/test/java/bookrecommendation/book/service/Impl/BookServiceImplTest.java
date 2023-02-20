package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.service.BookService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    void searchBooksByQuery() {
    }

    @Test
    void searchBooksByCategory() {
        String interest = "IT";

        List<BookDto> bookDtos = bookService.searchByCategory(interest);
        for (BookDto bookDto : bookDtos) {
            System.out.println("bookDto = " + bookDto.getTitle());
        }

    }

    @Test
    void searchBooksByAgeandGender() {
        int age = 24;
        Gender gender = Gender.FEMALE;

        List<BookDto> results = bookService.searchByAgeAndGender(age, gender);
        for (BookDto result : results) {
            System.out.println("result.getTitle() = " + result.getTitle());
        }
    }

    @Test
    void searchBooksByFeeling(){
        String feeling = "기쁨";

        List<BookDto> results = bookService.searchByFeeling(feeling);
        for (BookDto result : results) {
            System.out.println("result = " + result.getTitle());
        }
    }

    @Test
    void getAladinBookResults() {
    }
}