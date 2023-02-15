package hello.hellospring.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookSearchQueryParams {
    private String key;
    private String query;
    private String queryType;
    private int maxResults;
    private String sort;
    private String coverSize;
    private String output;
    private int categoryId;
    private int version;

    public BookSearchQueryParams(){
        setKey("ttbyebin2lee1424001");
        setMaxResults(10);
        setSort("SalesPoint");
        setCoverSize("Mid");
        setOutput("js");
        setQueryType("BestSeller");
        setVersion(20131101);
    }
}
