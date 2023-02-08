package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookDto;

public interface AladinApiService {
    BookDto searchByCategory(String interest);
    BookDto searchByFeeling(String feeling);

}
