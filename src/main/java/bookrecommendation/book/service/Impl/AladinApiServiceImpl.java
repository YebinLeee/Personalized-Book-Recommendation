package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Feeling;
import bookrecommendation.book.domain.Interest;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.openapi.AladinParamSetter;
import bookrecommendation.book.openapi.params.AladinQueryParams;
import bookrecommendation.book.service.AladinApiService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bookrecommendation.book.openapi.constant.ApiUrlConstant.AlADIN_ITEM_LIST_API_URL;


@Component
public class AladinApiServiceImpl implements AladinApiService {

    private final AladinBookResultService aladinBookResultService;

    @Autowired
    public AladinApiServiceImpl(AladinBookResultService aladinBookResultService) {
        this.aladinBookResultService = aladinBookResultService;
    }


    @Override
    public List<BookDto> searchByCategory(String interestValue) {
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();

        Interest interest  = Interest.valueOfDescription(interestValue);
        int categoryId = getCategoryByInterest(interest);

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

        return getResults(new JSONObject(response));
    }

    private static int getCategoryByInterest(Interest interest) {
        int categoryId = 0;
        switch (Objects.requireNonNull(interest)) {
            case SELF_IMPROVEMENT -> categoryId = 336;
            case HOBBY -> categoryId = 558890;
            case TRAVEL -> categoryId = 51377;
            case COOKBOOK -> categoryId = 53471;
            case EMPLOYMENT -> categoryId = 249;
            case WORK -> categoryId = 2172;
            case EXAMINEES -> categoryId = 1383;
            case FOREIGN_LANGUAGES-> categoryId = 1322;
            case RELIGION -> categoryId = 17436;
            case SPORTS -> categoryId = 8097;
            case IT -> categoryId = 8537;
            case NATURE -> categoryId = 8260;
            case ARTS -> categoryId = 518;
            case SOCIOLOGY -> categoryId = 51306;
            case SCIENCE -> categoryId = 887;
            default -> {
            }
        }
        return categoryId;
    }

    @Override
    public List<BookDto> searchByFeeling(String feelingValue) {
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();

        Feeling feeling = Feeling.valueOfString(feelingValue);

        params.setCategoryId(switch (Objects.requireNonNull(feeling)) {
            case SADNESS, FEAR, DEPRESSED, ANGER, ANXIETY, REGRET -> 51375;
            default -> 50940;
        });

        paramSetter.setRequestUrl(AlADIN_ITEM_LIST_API_URL);

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getResults(new JSONObject(response));

    }

    @Override
    public List<BookDto> getResults(JSONObject jsonObject) {
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
