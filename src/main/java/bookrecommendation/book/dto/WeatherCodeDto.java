package bookrecommendation.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherCodeDto {
    @JsonProperty(value = "weather_code")
    private int code;
}
