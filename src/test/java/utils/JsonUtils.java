package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    static ObjectMapper mapper = new ObjectMapper();

    public static Object convertToObject(String json, Class output) throws JsonProcessingException {
        return mapper.readValue(json, output);
    }
}
