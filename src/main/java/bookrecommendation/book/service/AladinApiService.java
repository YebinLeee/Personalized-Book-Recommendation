package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookDto;

import java.util.List;

public interface AladinApiService {
    List<BookDto> searchByInterest(String interestValue);
    List<BookDto> searchByFeeling(List<String> feelings);
}
