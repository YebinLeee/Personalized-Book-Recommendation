package bookrecommendation.book.service;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;

import java.util.List;

public interface LibraryBigDataApiService {
    List<BookDto> searchByAgeAndGender(int age, Gender gender);
}
