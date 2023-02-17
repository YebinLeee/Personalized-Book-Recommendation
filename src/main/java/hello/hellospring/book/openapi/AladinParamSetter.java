package hello.hellospring.book.openapi;

import hello.hellospring.book.openapi.dto.AladinQueryParams;
import hello.hellospring.service.constant.ConstantValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@Component
public class AladinParamSetter {
    private String requestUrl;

    public String setParams(AladinQueryParams params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("ttbkey", params.getKey())
                .queryParam("maxResults", params.getMaxResults())
                .queryParam("sort", params.getSort())
                .queryParam("cover", params.getCoverSize())
                .queryParam("output", params.getOutput())
                .queryParam("categoryId", params.getCategoryId())
                .queryParam("version", params.getVersion());

        if (requestUrl.equals(ConstantValue.AlADIN_ITEM_SEARCH_API_URL)){
            uriComponentsBuilder.queryParam("query", params.getQuery());
        }
        else if (requestUrl.equals(ConstantValue.AlADIN_ITEM_LIST_API_URL)){
            uriComponentsBuilder.queryParam("queryType", params.getQueryType());
        }

        return uriComponentsBuilder.toUriString();
    }
}
