package bookrecommendation.book.service.Impl;

import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.service.BookResultService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryBigDataBookResultService implements BookResultService {
    @Override
    public List<BookDto> getResult(JSONObject jsonObject) {
        List<BookDto> results = new ArrayList<>();
        JSONObject response = jsonObject.getJSONObject("response");
        JSONArray items = response.getJSONArray("docs");

        for (int i = 0; i < items.length(); i++) {
            BookDto result = new BookDto();
            JSONObject item = items.getJSONObject(i);
            JSONObject doc = item.getJSONObject("doc");

            result.setTitle(doc.getString("bookname"));
            result.setLink(doc.getString("bookDtlUrl"));
            result.setAuthor(doc.getString("authors"));
            result.setPubDate(doc.getString("publication_year"));
            result.setIsbn(doc.getString("isbn13"));
            result.setCategoryId(doc.getString("class_no"));
            result.setCategoryName(doc.getString("class_nm"));
            result.setPublisher(doc.getString("publisher"));
            result.setCoverLink(doc.getString("bookImageURL"));

            results.add(result);
        }
        return results;
    }
}
