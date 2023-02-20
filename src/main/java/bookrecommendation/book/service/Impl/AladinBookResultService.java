package bookrecommendation.book.service.Impl;

import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.service.BookResultService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AladinBookResultService implements BookResultService {

    @Override
    public List<BookDto> getResult(JSONObject jsonObject) {
        List<BookDto> results = new ArrayList<>();
        System.out.println("jsonObject = " + jsonObject);
        JSONArray items = jsonObject.getJSONArray("item");

        for (int i = 0; i < items.length(); i++) {
            BookDto result = new BookDto();

            JSONObject item = items.getJSONObject(i);
            result.setTitle(item.getString("title"));
            result.setLink(item.getString("link"));
            result.setAuthor(item.getString("author"));
            result.setPubDate(item.getString("pubDate"));
            result.setIsbn(item.getString("isbn"));
            result.setCategoryId(String.valueOf(item.getLong("categoryId")));
            result.setCategoryName(item.getString("categoryName"));
            result.setPublisher(item.getString("publisher"));
            result.setCoverLink(item.getString("cover"));
            results.add(result);
        }
        return results;
    }
}
