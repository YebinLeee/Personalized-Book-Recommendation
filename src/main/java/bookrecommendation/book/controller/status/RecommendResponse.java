package bookrecommendation.book.controller.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendResponse {
    private int code;
    private String message;
    private Object data;

    public RecommendResponse(){
        this.code = StatusCode.BAD_REQUEST;
        this.message = ResponseMessage.API_CALL_ERROR;
        this.data = null;
    }
}
