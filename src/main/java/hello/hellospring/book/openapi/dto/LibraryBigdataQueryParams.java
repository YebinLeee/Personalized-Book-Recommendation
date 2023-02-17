package hello.hellospring.book.openapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LibraryBigdataQueryParams {
    // startDt=2022-11-01&endDt=2023-02-08&gender=0&age=14&region=11&format=json&pageSize=2

    private String authKey;
    private String startDate;
    private int gender;
    private int age;
    private int pageSize;
    private String format;

    public LibraryBigdataQueryParams(){
        setAuthKey("b7b49b6c39fad7cc4beb5f83cd5199902a8b8d4ae96fa9cf523b332c0cca3633");
        setStartDate("2022-01-01");
        setGender(2);
        setAge(-1);
        setPageSize(10);
        setFormat("json");
    }
}
