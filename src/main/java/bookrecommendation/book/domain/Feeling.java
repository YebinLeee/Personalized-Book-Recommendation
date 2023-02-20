package bookrecommendation.book.domain;

import lombok.Getter;

@Getter
public enum Feeling {
    HAPPINESS("기쁨", 1),
    JOY("즐거움",2),
    COMFORT("편안함", 3),
    CHALLENGING("도전적임", 4),
    GRATEFULNESS("감사함", 5),
    SATISFACTION("만족스러움",6),
    SADNESS("슬픔",7),
    FEAR("두려움", 8),
    DEPRESSED("우울함",9),
    ANGER("분노",10),
    ANXIETY("불안함",11),
    REGRET("후회",12);

    private final String stringValue;
    private final int code;

    Feeling(String stringValue, int code) {
        this.stringValue = stringValue;
        this.code = code;
    }

    public static Feeling valueOfString(String stringValue){
        for (Feeling feeling : values()){
            if(feeling.stringValue.equals(stringValue)){
                return feeling;

            }
        }
        return null;
    }

    public static Feeling valueOfCode(int code){
        for(Feeling feeling:values()){
            if(feeling.code==code){
                return feeling;
            }
        }
        return null;
    }
}
