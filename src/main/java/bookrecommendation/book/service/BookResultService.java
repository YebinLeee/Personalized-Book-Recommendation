package bookrecommendation.book.service;

import bookrecommendation.book.dto.BookDto;
import org.json.JSONObject;

import java.util.List;

public interface BookResultService {
    List<BookDto> getResult(JSONObject jsonObject);

}
