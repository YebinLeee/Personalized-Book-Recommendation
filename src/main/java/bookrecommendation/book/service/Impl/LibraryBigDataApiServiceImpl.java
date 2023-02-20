package bookrecommendation.book.service.Impl;

import bookrecommendation.book.domain.Gender;
import bookrecommendation.book.dto.BookDto;
import bookrecommendation.book.openapi.LibraryBigdataParamSetter;
import bookrecommendation.book.openapi.params.LibraryBigdataQueryParams;
import bookrecommendation.book.service.LibraryBigDataApiService;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static bookrecommendation.book.openapi.constant.ApiUrlConstant.LIBRARY_BIGDATA_LOAN_API_URL;

@Component
public class LibraryBigDataApiServiceImpl implements LibraryBigDataApiService {

    private final LibraryBigDataBookResultService libraryBigDataBookResultService;

    @Autowired
    public LibraryBigDataApiServiceImpl(LibraryBigDataBookResultService libraryBigDataBookResultService) {
        this.libraryBigDataBookResultService = libraryBigDataBookResultService;
    }

    @Override
    public List<BookDto> searchByAgeAndGender(int age, Gender gender) {
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

        return libraryBigDataBookResultService.getResult(new JSONObject(response));
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
}
