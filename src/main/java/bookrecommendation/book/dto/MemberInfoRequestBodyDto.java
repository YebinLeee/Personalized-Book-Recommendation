package bookrecommendation.book.dto;

import bookrecommendation.book.domain.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class MemberInfoRequestBodyDto {
    private int id;
    private int age;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("feeling_code")
    private ArrayList<String> feelings;
    @JsonProperty("interest_code")
    private String interest;
}
