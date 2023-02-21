package bookrecommendation.book.openapi;

import bookrecommendation.book.openapi.params.WeatherQueryParams;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

@Setter
@Getter
public class WeatherOpenDataParamSetter {
    private String requestUrl;
    public String setParams(WeatherQueryParams params) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("serviceKey", params.getServiceKey())
                .queryParam("nx", params.getNx())
                .queryParam("ny", params.getNy())
                .queryParam("base_date", params.getBaseDate())
                .queryParam("base_time", params.getBaseTime())
                .queryParam("dataType", params.getDataType());

        return uriComponentsBuilder.toUriString();
    }
}
