import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Mapper {

    public static  <T> String  mapObjeectToString(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static  <RESULT> RESULT getMarketingResponseFromJson(String string, Class<RESULT> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(string, clazz);
}
}
