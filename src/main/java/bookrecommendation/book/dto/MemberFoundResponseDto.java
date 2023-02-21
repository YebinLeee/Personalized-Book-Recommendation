package bookrecommendation.book.dto;

import bookrecommendation.book.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFoundResponseDto {
    private String id;
    private String name;
    private Gender gender;
    private int age;

    public MemberFoundResponseDto() {
        setId(String.valueOf(0L));
    }
}
