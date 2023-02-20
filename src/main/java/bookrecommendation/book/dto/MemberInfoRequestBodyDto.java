package bookrecommendation.book.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberInfoRequestBodyDto {
    private String id;
    private String feeling1_code;
    private String feeling2_code;
    private String feeling3_code;
    private String interest_code;
}
