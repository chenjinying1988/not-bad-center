package com.jychan.notbad.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by chenjinying on 2017/5/14.
 * mail: 415683089@qq.com
 */
public final class JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final ObjectMapper UNICODE_MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UNICODE_MAPPER.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        UNICODE_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private static final String EMPTY_JSON = "{}";

    /**
     * Convert json to an instance of a specific type
     *
     * @param json Json to be parsed
     * @param type Type to be casted
     * @return
     */
    public static final <T> T fromJson(String json, Class<T> type) {
        return fromJson(json, type, false);
    }

    /**
     * Convert json to an instance of a speicific type, if null return a new instance.
     */
    public static final <T> T fromJson(String json, Class<T> type, boolean newInstance) {
        return fromJson(MAPPER, json, type, newInstance);
    }

    public static final <T> T fromJson(ObjectMapper mapper, String json, Class<T> type) {
        return fromJson(mapper, json, type, false);
    }

    /**
     * Convert json to an instance of a specific type with an ObjectMapper instance
     *
     * @param mapper ObjectMapper instance use for parsing the json
     * @param json   Json to be parsed
     * @param type   Type to be casted
     * @return
     */
    public static final <T> T fromJson(ObjectMapper mapper, String json, Class<T> type, boolean newInstance) {
        try {
            if (StringUtils.isBlank(json))
                return newInstance ? type.newInstance() : null;
            T result = mapper.readValue(json, type);
            return result == null && newInstance ? type.newInstance() : result;
        }
        catch (Exception e) {
            LOG.warn("[fromJson] Could not parse json -> {}, to type -> {}", json, type,e);
        }
        return null;
    }

    /**
     * Convert json to a speific type, and no new instance return.
     */
    public static final <T> T fromJson(String json, TypeReference<T> type) {
        return fromJson(MAPPER, json, type, false);
    }

    /**
     * Convert json to a speific type, or new instance of that type return.
     */
    public static final <T> T fromJson(String json, TypeReference<T> type, boolean newInstance) {
        return fromJson(MAPPER, json, type, newInstance);
    }

    /**
     * Convert json to an instance of a specific type with an ObjectMapper instance
     *
     * @param mapper ObjectMapper instance use for parsing the json
     * @param json   Json to be parsed
     * @param type   Type to be casted
     * @return
     */
    @SuppressWarnings("unchecked")
    public static final <T> T fromJson(ObjectMapper mapper, String json, TypeReference<T> type, boolean newInstance) {
        if (type == null)
            return null;
        try {
            if (StringUtils.isBlank(json))
                return newInstance ? (T) type.getType().getClass().newInstance() : null;
            T result = mapper.readValue(json, type);
            return result == null && newInstance ? (T) type.getType().getClass().newInstance() : result;
        }
        catch (Exception e) {
            LOG.warn("[fromJson] Could not parse json -> {}, to type -> {}", json, type);
        }
        return null;
    }

    public static void main(String[] args) {
        fromJson(MAPPER, "[{\"a\":1,\"b\":2}]", new TypeReference<List<Map<String, Object>>>() {
        }, true);
    }

    /**
     * Transform object to json format string as unicode
     */
    public static final String toJsonUnicode(Object obj) {
        return toJson(UNICODE_MAPPER, obj);
    }

    /**
     * Transform object to json format string
     */
    public static final String toJson(Object obj) {
        return toJson(MAPPER, obj);
    }

    public static final Optional<String> toJsonOptional(Object obj){
        return toJsonOptional(MAPPER, obj);
    }

    public static final Optional<String> toJsonOptional(ObjectMapper mapper, Object obj){
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        }
        catch (Exception e) {
            LOG.warn("[toJson] Object[{}] could not parse to json", obj);
        }

        return Optional.ofNullable(json);
    }

    /**
     * Transform object to json format string using the specific ObjectMapper
     *
     * @param mapper ObjectMapper instance to use to transform an Object to json
     * @param obj    Object to be parsed to json, or {} return.
     * @return
     */
    public static final String toJson(ObjectMapper mapper, Object obj) {

        return toJsonOptional(mapper, obj).orElse(EMPTY_JSON);
    }

    /**
     * Whether given string is legal json
     */
    public static boolean isJson(String json) {
        try {
            //Try to parse it
            JsonObjectMapper.getObjectMapper().readTree(json);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    /**
     * To JsonNode
     */
    public static JsonNode toJsonNode(String json) {
        try {
            return JsonObjectMapper.getObjectMapper().readTree(json);
        }
        catch (IOException e) {
            return null;
        }
    }

    /**
     * From JsonNode to Object
     */
    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) {
        try {
            return JsonObjectMapper.getObjectMapper().treeToValue(node, clazz);
        }
        catch (Exception e) {
            return null;
        }
    }
}
