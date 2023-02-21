package bookrecommendation.book.service;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookDto> searchByInterest(String interest);
    List<BookDto> searchByAgeAndGender(int age, Gender gender);
    List<BookDto> searchByFeeling(List<String> feelings);
}
