package hello.hellospring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "RFID_NUMBER")
    private String rfid;

    @Column(name = "BARCODE_NUMBER")
    private String barcode;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
