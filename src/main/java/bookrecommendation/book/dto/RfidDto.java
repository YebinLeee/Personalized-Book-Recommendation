package bookrecommendation.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RfidDto {
    @JsonProperty("rfid_id")
    private String rfid;
}
