package hello.hellospring.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookSearchQueryParams {
    private String key;
    private String query;
    private int maxResults;
    private String sort;
    private String coverSize;
    private String output;

    public BookSearchQueryParams(){
        this.key = "ttbyebin2lee1424001";
        this.maxResults = 5;
        this.sort = "SalesPoint";
        this.coverSize = "Big";
        this.output = "xml";
    }
}
