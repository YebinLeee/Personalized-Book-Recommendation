package bookrecommendation.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Interest {
    SELF_IMPROVEMENT("자기계발"),
    HOBBY("취미"),
    TRAVEL("여행"),
    COOKBOOK("요리"),
    EMPLOYMENT("취업"),
    WORK("직장"),
    EXAMINEES("수험생"),
    FOREIGN_LANGUAGES("외국어"),
    RELIGION("종교"),
    SPORTS("스포츠"),
    IT("IT"),
    NATURE("자연"),
    ARTS("예술"),
    SOCIOLOGY("사회"),
    SCIENCE("과학");

    private String description;
}
