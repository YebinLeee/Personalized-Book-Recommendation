package bookrecommendation.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member_tbl")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "rfid_serial_number")
    private String rfid;

    @Column(name = "barcode_number")
    private String barcode;

    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
