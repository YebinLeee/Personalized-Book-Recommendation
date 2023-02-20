package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.service.AladinApiService;
import bookrecommendation.book.service.BookService;
import bookrecommendation.book.service.LibraryBigDataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class BookServiceImpl implements BookService {

    private final AladinApiService aladinApiService;
    private final LibraryBigDataApiService libraryBigDataApiService;

    @Autowired
    public BookServiceImpl(AladinApiService aladinApiService, LibraryBigDataApiService libraryBigDataApiService) {
        this.aladinApiService = aladinApiService;
        this.libraryBigDataApiService = libraryBigDataApiService;
    }

    @Override
    public List<BookDto> searchByCategory(String interest) {
        return aladinApiService.searchByCategory(interest);
    }

    @Override
    public List<BookDto> searchByAgeAndGender(int age, Gender gender) {
        return libraryBigDataApiService.searchByAgeAndGender(age, gender);
    }

    @Override
    public List<BookDto> searchByFeeling(String feeling) {
        return aladinApiService.searchByFeeling(feeling);
    }
}
