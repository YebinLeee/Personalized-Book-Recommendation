package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.dto.BookSearchQuery;
import bookrecommendation.book.openapi.AladinParamSetter;
import bookrecommendation.book.openapi.params.AladinQueryParams;
import bookrecommendation.book.service.AladinApiService;
import bookrecommendation.book.service.BookService;
import bookrecommendation.book.service.LibraryBigDataApiService;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static bookrecommendation.book.openapi.constant.ApiUrlConstant.AlADIN_ITEM_SEARCH_API_URL;

@Component
@Primary
public class BookServiceImpl implements BookService {

    private final AladinApiService aladinApiService;
    private final LibraryBigDataApiService libraryBigDataApiService;

    @Autowired
    public BookServiceImpl(AladinApiService aladinApiService, LibraryBigDataApiService libraryBigDataApiService) {
        this.aladinApiService = aladinApiService;
        this.libraryBigDataApiService = libraryBigDataApiService;
    }

    @Override
    public List<BookDto> searchByQuery(BookSearchQuery query) {
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

        return aladinApiService.getResults(new JSONObject(response));
    }

    @Override
    public List<BookDto> searchByCategory(String interest) {
        return aladinApiService.searchByCategory(interest);
    }

    @Override
    public List<BookDto> searchByAgeAndGender(int age, Gender gender) {
        return libraryBigDataApiService.searchByAgeAndGender(age, gender);
    }

    @Override
    public List<BookDto> searchByFeeling(String feeling) {
        return aladinApiService.searchByFeeling(feeling);
    }
}
