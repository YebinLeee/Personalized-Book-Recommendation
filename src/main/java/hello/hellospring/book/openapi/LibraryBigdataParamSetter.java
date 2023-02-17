package hello.hellospring.book.openapi;

import hello.hellospring.book.openapi.dto.LibraryBigdataQueryParams;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class LibraryBigdataParamSetter {
    private String requestUrl;

    public String setParams(LibraryBigdataQueryParams params){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("authKey", params.getAuthKey())
                .queryParam("startDt", params.getStartDate())
                .queryParam("gender", params.getGender())
                .queryParam("age", params.getAge())
                .queryParam("pageSize", params.getPageSize())
                .queryParam("format", params.getFormat());

        return uriComponentsBuilder.toUriString();
    }
}
