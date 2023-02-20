package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookDto;
import org.json.JSONObject;

import java.util.List;

public interface AladinApiService {
    List<BookDto> searchByCategory(String interestValue);
    List<BookDto> searchByFeeling(String feelingValue);
    List<BookDto> getResults(JSONObject jsonObject);
}
