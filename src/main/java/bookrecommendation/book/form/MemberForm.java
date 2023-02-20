package bookrecommendation.book.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String rfid;
    private String barcode;
    private String password;
    private String name;
    private int age;
    private String gender;

}
