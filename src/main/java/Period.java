import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class Period
{
   // @JsonProperty
    private OffsetDateTime start;
  //      @JsonProperty
    private OffsetDateTime end;
}
