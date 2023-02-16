package hello.hellospring.book.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberConfirmForm {
    private String rfid;
    private String barcode;
}
