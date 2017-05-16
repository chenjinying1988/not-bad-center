package com.jychan.notbad.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by chenjinying on 2017/5/14.
 * mail: 415683089@qq.com
 */
public class JsonObjectMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private JsonObjectMapper() {
        // none
    }

    public static final ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
