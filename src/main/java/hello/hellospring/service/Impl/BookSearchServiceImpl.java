package hello.hellospring.service.Impl;

import hello.hellospring.book.dto.BookSearchQuery;
import hello.hellospring.book.dto.BookSearchQueryParams;
import hello.hellospring.book.dto.BookSearchedResult;
import hello.hellospring.service.BookSearchService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hello.hellospring.service.constant.ConstantValue.AlADIN_ITEM_LIST_API_URL;
import static hello.hellospring.service.constant.ConstantValue.AlADIN_ITEM_SEARCH_API_URL;

public class BookSearchServiceImpl implements BookSearchService {

    public List<BookSearchedResult> searchBooksByQuery(BookSearchQuery query) {
        BookSearchQueryParams params = new BookSearchQueryParams();
        params.setQuery(query.getQuery());

        AladinParamSetter aladinParamSetter = new AladinParamSetter(AlADIN_ITEM_SEARCH_API_URL);
        String requestUrl = aladinParamSetter.set(params);
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

        AladinParamSetter aladinParamSetter = new AladinParamSetter(AlADIN_ITEM_LIST_API_URL);
        String requestUrl = aladinParamSetter.set(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getBookResults(new JSONObject(response));
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