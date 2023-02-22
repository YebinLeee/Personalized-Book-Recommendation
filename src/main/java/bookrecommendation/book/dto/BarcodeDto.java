package bookrecommendation.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarcodeDto {
    @JsonProperty("barcode_id")
    private String barcode;
}
