package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.dto.BookSearchQuery;
import bookrecommendation.book.openapi.AladinParamSetter;
import bookrecommendation.book.openapi.LibraryBigdataParamSetter;
import bookrecommendation.book.openapi.params.AladinQueryParams;
import bookrecommendation.book.openapi.params.LibraryBigdataQueryParams;
import bookrecommendation.book.service.BookSearchService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static bookrecommendation.book.openapi.constant.ApiUrlConstant.*;

@Component
public class BookSearchServiceImpl implements BookSearchService {

    @Override
    public List<BookDto> searchBooksByQuery(BookSearchQuery query) {
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();

        params.setQuery(query.getQuery());
        paramSetter.setRequestUrl(AlADIN_ITEM_SEARCH_API_URL);

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getAladinBookResults(new JSONObject(response));
    }

    @Override
    public List<BookDto> searchBooksByCategory(String interest){
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();

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

        params.setCategoryId(categoryId);
        paramSetter.setRequestUrl(AlADIN_ITEM_LIST_API_URL);

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getAladinBookResults(new JSONObject(response));
    }

    @Override
    public List<BookDto> searchBooksByAgeandGender(int age, Gender gender) {
        LibraryBigdataQueryParams params = new LibraryBigdataQueryParams();
        LibraryBigdataParamSetter paramSetter = new LibraryBigdataParamSetter();

        setAgeParam(age, params);
        setGenderParam(gender, params);

        paramSetter.setRequestUrl(LIBRARY_BIGDATA_LOAN_API_URL);

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getLibraryBigdataBookResults(new JSONObject(response));
    }

    private static void setGenderParam(Gender gender, LibraryBigdataQueryParams params) {
        switch(gender){
            case MALE -> params.setGender(0);
            case FEMALE -> params.setGender(1);
            default -> params.setGender(2);
        }
    }

    private static void setAgeParam(int age, LibraryBigdataQueryParams params) {
        if(age <=5)
            params.setAge(0);
        else if(age <=7)
            params.setAge(6);
        else if(age <=13)
            params.setAge(8);
        else if(age <=19)
            params.setAge(14);
        else if(age <=29)
            params.setAge(20);
        else if(age <=39)
            params.setAge(30);
        else if(age <=49)
            params.setAge(40);
        else if(age <=59)
            params.setAge(50);
        else params.setAge(60);
    }


    @Override
    public List<BookDto> searchBooksByFeeling(String feeling) {
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();

        switch (feeling) {
            case "슬픔", "두려움", "우울함", "분노", "불안함", "후회" -> params.setCategoryId(51375);
            default -> params.setCategoryId(50940);
        }

        paramSetter.setRequestUrl(AlADIN_ITEM_LIST_API_URL);

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getAladinBookResults(new JSONObject(response));
    }

    private static List<BookDto> getAladinBookResults(JSONObject jsonObject){
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

    private static List<BookDto> getLibraryBigdataBookResults(JSONObject jsonObject){
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