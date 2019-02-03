import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.val;
import org.junit.Test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MapperTest {

/*    @Test
    public void mapObjectToStringTest() throws JsonProcessingException {
        System.out.println(Mapper.mapObjeectToString(getMockMarketingResponse()));
    }

    @Test
    public void mapStringToObjectTest() throws IOException {
        val jsonString = Mapper.mapObjeectToString(getMockMarketingResponse());
        Mapper.getMarketingResponseFromJson(jsonString, MarketingResponse.class);
    }*/

/*    @Test
    public void offsetDTToJSonString() throws IOException {
        User user = new User("Roma", "Luch", OffsetDateTime.parse("2011-12-03T10:15:30+01:00",DateTimeFormatter.ISO_DATE_TIME));
        val jsonCasualMapper = Mapper.mapObjeectToString(user);
        System.out.println(jsonCasualMapper);
        val jsonFromCustomMapper = objectMapper().writeValueAsString(user);
        System.out.println(jsonFromCustomMapper);
    }*/

/*    @Test
    public void jsonStringToOffsetDT() throws IOException {
        User user = new User("Roma", "Luch", OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_DATE_TIME));
        val jsonFromCustomMapper = objectMapper().writeValueAsString(user);
        //System.out.println(Mapper.getMarketingResponseFromJson(jsonFromCustomMapper, User.class));
        User user1 = objectMapper().readValue(jsonFromCustomMapper, User.class);
        System.out.println(user1);
    }*/

    @Test
    public void serializableListToJson() throws IOException {
        OffsetDateTime date1 = OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        OffsetDateTime date2 = OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Period date = new Period(date1, date2);
        List<User> list = new ArrayList<>(
                Arrays.asList(
                        new User("1", "11", date)
                        , new User("2", "22", date)
                        , new User("3", "33", date)));

        ObjectMapper mapper = objectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
        };

        String jsonListOfUsers = mapper.writeValueAsString(list);
        System.out.println(jsonListOfUsers);

        List<User> usersList = mapper.readValue(jsonListOfUsers, typeReference);
        System.out.println(usersList);
    }


/*    public MarketingResponse getMockMarketingResponse() {

        OffsetDateTime begin = OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_DATE_TIME);
        OffsetDateTime end = OffsetDateTime.parse("2012-12-03T10:15:30+01:00", DateTimeFormatter.ISO_DATE_TIME);

        return MarketingResponse
                .builder()
                .title("2011-12-03T10:15:30+01:00")
                .period(new Period("2011-12-03T10:15:30+01:00", "2012-12-03T10:15:30+01:00"))
                .build();
    }*/

    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
            @Override
            public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));

            }
        });
        simpleModule.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return OffsetDateTime.parse(p.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }
        });
        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }

}
