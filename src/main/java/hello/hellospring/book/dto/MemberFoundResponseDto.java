package hello.hellospring.book.dto;

import hello.hellospring.domain.Gender;
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
