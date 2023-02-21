package bookrecommendation.book.controller.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
    private int code;
    private String message;
    private Object data;

    public WeatherResponse(){
        this.code = StatusCode.BAD_REQUEST;
        this.message = ResponseMessage.API_CALL_ERROR;
        this.data = null;
    }
}
