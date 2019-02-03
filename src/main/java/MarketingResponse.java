import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor()
@AllArgsConstructor
public class MarketingResponse {
    @JsonProperty
    private String title;
    @JsonProperty()
    private Period period;
}
