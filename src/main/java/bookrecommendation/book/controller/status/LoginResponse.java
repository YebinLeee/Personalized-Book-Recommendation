package bookrecommendation.book.controller.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private int code;
    private String message;
    private Object data;

    public LoginResponse(){
        this.code = StatusCode.NOT_FOUND;
        this.message = ResponseMessage.LOGIN_FAILED;
        this.data = null;
    }
}
