package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Feeling;
import bookrecommendation.book.domain.Interest;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.openapi.AladinParamSetter;
import bookrecommendation.book.openapi.params.AladinQueryParams;
import bookrecommendation.book.service.AladinApiService;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static bookrecommendation.book.domain.Feeling.*;
import static bookrecommendation.book.openapi.constant.ApiUrlConstant.AlADIN_ITEM_LIST_API_URL;

@Component
@Primary
public class AladinApiServiceImpl implements AladinApiService {
    private final AladinBookResultService aladinBookResultService;

    @Autowired
    public AladinApiServiceImpl(AladinBookResultService aladinBookResultService) {
        this.aladinBookResultService = aladinBookResultService;
    }

    @Override
    public List<BookDto> searchByInterest(String interestValue) {
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

        return aladinBookResultService.getResult(new JSONObject(response));
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
    public List<BookDto> searchByFeeling(List<String> feelings) {
        AladinQueryParams params = new AladinQueryParams();
        AladinParamSetter paramSetter = new AladinParamSetter();
        List<Feeling> negativeFeelings = new ArrayList<>(Arrays.asList(SADNESS, FEAR, DEPRESSED, ANGER, ANXIETY, REGRET));

        paramSetter.setRequestUrl(AlADIN_ITEM_LIST_API_URL);
        params.setCategoryId(50940);

        for (String feelingValue : feelings) {
            Feeling feeling = Feeling.valueOfString(feelingValue);
            for (Feeling negativeFeeling: negativeFeelings) {
                if (feeling.equals(negativeFeeling)) {
                    params.setCategoryId(51375);
                    break;
                }
            }
        }

        String requestUrl = paramSetter.setParams(params);
        Connection connection = Jsoup.connect(requestUrl);

        String response;
        try {
            response = connection.ignoreContentType(true).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return aladinBookResultService.getResult(new JSONObject(response));
    }
}
