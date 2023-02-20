package bookrecommendation.book.dto;

import bookrecommendation.book.domain.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFoundResponseDto {
    private char is_authenticated;
    private Long id;
    private String name;
    private Gender gender;
    private int age;

    public MemberFoundResponseDto() {
        setId(0L);
    }
}
