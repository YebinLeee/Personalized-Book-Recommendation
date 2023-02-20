package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookSearchQuery;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.domain.Gender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookSearchService {

    List<BookDto> searchBooksByQuery(BookSearchQuery query);
    List<BookDto> searchBooksByCategory(String interest);
    List<BookDto> searchBooksByAgeandGender(int age, Gender gender);
    List<BookDto> searchBooksByFeeling(String feeling);
}
