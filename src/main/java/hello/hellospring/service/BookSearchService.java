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
    private static String itemListUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private static String itemSearchUrl = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

    @Autowired
    public BookSearchService() {
    }


    public List<BookSearchedResult> searchBooks(BookSearchQuery query) {
        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setQuery(query.getQuery());

        String requestUrl = setParameters(itemSearchUrl, params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getBookResults(new JSONObject(response));
    }

    private String setParameters(String requestUrl, BookSearchQueryParams params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("ttbkey", params.getKey())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput())
                .queryParam("categoryId", params.getCategoryId())
                .queryParam("version", params.getVersion());

        if (requestUrl.equals(itemSearchUrl)){
            uriComponentsBuilder.queryParam("query", params.getQuery());
        }
        else if (requestUrl.equals(itemListUrl)){
            uriComponentsBuilder.queryParam("queryType", params.getQueryType());
        }

        System.out.println("requestUrl = " + requestUrl);

        String url = uriComponentsBuilder.toUriString();
        System.out.println("url = " + url);

        return url;
    }

    public List<BookSearchedResult> getBookResults(JSONObject jsonObject){
        List<BookSearchedResult> results = new ArrayList<>();
        System.out.println("jsonObject = " + jsonObject);

        JSONArray items = jsonObject.getJSONArray("item");
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
            result.setCoverLink(item.getString("cover"));
            results.add(result);
        }
        return results;
    }
}