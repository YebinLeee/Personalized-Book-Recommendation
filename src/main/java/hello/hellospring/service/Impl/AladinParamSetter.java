package hello.hellospring.service.Impl;

import hello.hellospring.book.dto.BookSearchQueryParams;
import org.springframework.web.util.UriComponentsBuilder;

import static hello.hellospring.service.constant.ConstantValue.AlADIN_ITEM_LIST_API_URL;
import static hello.hellospring.service.constant.ConstantValue.AlADIN_ITEM_SEARCH_API_URL;

public class AladinParamSetter {
    private final String requestUrl;

    public AladinParamSetter(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String set(BookSearchQueryParams params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("ttbkey", params.getKey())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput())
                .queryParam("categoryId", params.getCategoryId())
                .queryParam("version", params.getVersion());

        if (requestUrl.equals(AlADIN_ITEM_LIST_API_URL)){
            uriComponentsBuilder.queryParam("query", params.getQuery());
        }
        else if (requestUrl.equals(AlADIN_ITEM_SEARCH_API_URL)){
            uriComponentsBuilder.queryParam("queryType", params.getQueryType());
        }

        return uriComponentsBuilder.toUriString();
    }

}
