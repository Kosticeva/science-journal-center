package com.uns.ftn.sciencejournal.service.utils;

import java.util.Map;

public class ElasticSearchJsonUtil {

    private final String QUERY_ATTR = "query";
    private final String QUERY_ATTR_TYPE = "bool";

    private final String PHRASE_ATTR = "match_phrase";
    private final String CONDITION_ATTR = "match";

    private final String TYPE_MARK_AND = "must";
    private final String TYPE_MARK_OR = "should";

    private final String SIZE_PARAM = "size";

    public String generateAndQuery(Map<String, String> andParams) {
        return generateQuery(andParams, TYPE_MARK_AND);
    }

    public String generateOrQuery(Map<String, String> orParams) {
        return generateQuery(orParams, TYPE_MARK_OR);
    }

    private String generateQuery(Map<String, String> params, String type) {
        StringBuilder builder = new StringBuilder(String.format("\"%s\": { \"%s\": {\"%s\": [\n", QUERY_ATTR, QUERY_ATTR_TYPE, type));

        for (String key : params.keySet()) {
            if (params.get(key).trim().startsWith("\"")) {
                builder.append(generateQueryAttribute(key, params.get(key), PHRASE_ATTR));
            } else {
                builder.append(generateQueryAttribute(key, params.get(key), CONDITION_ATTR));
            }
        }

        builder.append("\n]}}");
        return builder.toString();
    }

    private String generateQueryAttribute(String key, String value, String condition) {
        return String.format("\t{\"%s\": { \"%s\": \"%s\" }},\n", condition, key, value);
    }

    public String generateSize(int size) {
        return "\"" + SIZE_PARAM + "\": " + size;
    }
}
