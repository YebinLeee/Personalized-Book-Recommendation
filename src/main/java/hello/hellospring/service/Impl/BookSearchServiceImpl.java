package hello.hellospring.service.Impl;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchQueryParams;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.service.BookSearchService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookSearchServiceImpl implements BookSearchService {

    private static final String itemListUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private static final String itemSearchUrl = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

    public List<BookSearchedResult> searchBooksByQuery(BookSearchQuery query) {
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

    public List<BookSearchedResult> searchBooksByCategory(String interest){
        int categoryId = 0;

        switch (interest) {
            case "자기계발" -> categoryId = 336;
            case "취미" -> categoryId = 558890;
            case "여행" -> categoryId = 51377;
            case "요리" -> categoryId = 53471;
            case "취업" -> categoryId = 249;
            case "직장" -> categoryId = 2172;
            case "수험생" -> categoryId = 1383;
            case "외국어" -> categoryId = 1322;
            case "종교" -> categoryId = 17436;
            case "스포츠" -> categoryId = 8097;
            case "IT" -> categoryId = 8537;
            case "자연" -> categoryId = 8260;
            case "예술" -> categoryId = 518;
            case "사회" -> categoryId = 51306;
            case "과학" -> categoryId = 887;
            default -> {
            }
        }

        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setCategoryId(categoryId);

        String requestUrl = setParameters(itemListUrl, params);
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

        return uriComponentsBuilder.toUriString();
    }

    public List<BookSearchedResult> getBookResults(JSONObject jsonObject){
        List<BookSearchedResult> results = new ArrayList<>();
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