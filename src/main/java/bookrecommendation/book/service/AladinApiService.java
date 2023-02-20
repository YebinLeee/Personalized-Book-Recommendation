package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookDto;

import java.util.List;

public interface AladinApiService {
    List<BookDto> searchByCategory(String interestValue);
    List<BookDto> searchByFeeling(String feelingValue);
}
