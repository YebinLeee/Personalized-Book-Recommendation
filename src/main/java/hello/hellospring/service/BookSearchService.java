package hello.hellospring.service;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchQueryParams;
import hello.hellospring.book.dto.BookSearchedResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookSearchService {

    @Autowired
    public BookSearchService() {
    }

    public List<BookSearchedResult> searchBooks(BookSearchQuery query) {
        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setQuery(query.getQuery());

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx")
                .queryParam("ttbkey", params.getKey())
                .queryParam("query", params.getQuery())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput());


        String url = uriComponentsBuilder.toUriString();
        Connection connection = Jsoup.connect(url);
        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONObject json = new JSONObject(response);
        JSONArray items = json.getJSONArray("item");
        ArrayList<BookSearchedResult> results = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            BookSearchedResult result = new BookSearchedResult();

            JSONObject item = items.getJSONObject(i);
            result.setTitle(item.getString("title"));
            result.setLink(item.getString("link"));
            result.setAuthor(item.getString("author"));
            result.setPubDate(item.getString("pubDate"));
            result.setIsbn(item.getString("isbn"));
            result.setCategoryId(item.getLong("categoryId"));
            result.setCategoryName(item.getString("categoryName"));
            result.setPublisher(item.getString("publisher"));
            result.setPriceSales(item.getLong("priceSales"));

            results.add(result);
        }

        return results;
    }
}