package com.uns.ftn.sciencejournal.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.model.PaperSearchModel;

import java.util.Map;

import static com.uns.ftn.sciencejournal.service.utils.ElasticSearchJsonConstants.*;

public class OldElasticSearchJsonUtil {

    public String generateMoreLikeThisQuery(String content){
        StringBuilder builder = new StringBuilder(String.format("{\"%s\": { \"%s\": { \"%s\": {", QUERY_ATTR, MORE_LIKE_THIS_ATTR, "content"));
        builder.append(String.format("\"%s\": \"%s\", ", LIKE_TEXT, content));
        builder.append(String.format("\"%s\": %s, ", MIN_TERM_FREQ, 15));
        builder.append(String.format("\"%s\": %s }}}}", MIN_DOC_FREQ, 1));

        return builder.toString();
    }

    public String generateGeoQuery(String latitude, String longitude) {
        StringBuilder builder = new StringBuilder(String.format("{\"%s\": {\"%s\": {\"%s\": {\"%s\": {}},\"%s\": {\"%s\": {\"%s\": {",
                QUERY_ATTR, FILTERED, QUERY_ATTR, CONDITION_ALL_ATTR, FILTER, NEGATION, GEO_DISTANCE));
        builder.append(String.format("\"%s\": \"%s\", \"%s\": {\"%s\": \"%s\", \"%s\": \"%s\"}}}}}}}", DISTANCE, "200km", "location", "lat", latitude, "lon", longitude));
        return builder.toString();
    }

    public String generateQueries(Map<String, String> andParameters, Map<String, String> orParameters, int size) {
        StringBuilder builder = new StringBuilder(String.format("{\"%s\": { \"%s\": {\n", QUERY_ATTR, QUERY_ATTR_TYPE));

        if (andParameters.size() > 0) {
            builder.append(generateQuery(andParameters, TYPE_MARK_AND));
        }

        if (andParameters.size() > 0 && orParameters.size() > 0) {
            builder.append("\n,\n");
        }

        if (orParameters.size() > 0) {
            builder.append(generateQuery(orParameters, TYPE_MARK_OR));
        }

        builder.append("\n}}");
        builder.append(generateHighlight());
        if (size > 0) {
            builder.append(String.format(",%s}", generateSize(size)));
        } else {
            builder.append("}");
        }

        return builder.toString();
    }

    private String generateQuery(Map<String, String> params, String type) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\"%s\": [\n", type));     //should/must

        int size = 0;

        for (String param : params.keySet()) {

            if (checkIfTermIsPhrase(params.get(param))) {
                builder.append(generateQueryAttribute(param, params.get(param), PHRASE_ATTR));
            } else {
                builder.append(generateQueryAttribute(param, String.format("\"%s\"", params.get(param)), CONDITION_ATTR));
            }

            if (size < params.size()-1) {
                builder.append(",\n");
                size++;
            }
        }

        builder.append("\n]");
        return builder.toString();
    }

    private boolean checkIfTermIsPhrase(String term) {
        return term.startsWith("\"") && term.endsWith("\"");
    }

    private String generateQueryAttribute(String key, String value, String condition) {
        return String.format("\t{\"%s\": { \"%s\": %s }}", condition, key, value);
    }

    private String generateHighlight(){
        return ",\"highlight\":{\"pre_tags\": [\"<b>\"],\"post_tags\": [\"</b>\"],\"fields\":{\"content\":{}}}";
    }

    private String generateSize(int size) {
        return "\"" + SIZE_PARAM + "\": " + size;
    }

    public JsonArray getHitsFromJson(String json) {
        Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class).get("hits").getAsJsonObject();
        return obj.get("hits").getAsJsonArray();
    }

    public String convertPaperSearchModelToJson(PaperSearchModel model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public String convertReviewerSearchModelToJson(PaperSearchModel.PaperReviewerSearchModel model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
