package com.uns.ftn.sciencejournal.service.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchJsonUtil {

    public String generateJsonFromBoolQuery(String boolQuery) {
        Gson gson = new Gson();

        JsonElement queryBase = parse(boolQuery);
        JsonElement query = new JsonObject();
        query.getAsJsonObject().add(ElasticSearchJsonConstants.QUERY_ATTR, queryBase);

        //",\"highlight\":{\"pre_tags\": [\"<b>\"],\"post_tags\": [\"</b>\"],\"fields\":{\"content\":{}}}";
        JsonElement highlightFields = new JsonObject();
        highlightFields.getAsJsonObject().add("content", new JsonObject());
        JsonElement highlight = new JsonObject();
        JsonArray preTags = new JsonArray();
        preTags.add("<b>");

        JsonArray postTags = new JsonArray();
        postTags.add("</b>");

        highlight.getAsJsonObject().add("pre_tags", preTags);
        highlight.getAsJsonObject().add("post_tags", postTags);
        highlight.getAsJsonObject().add("fields", highlightFields);

        query.getAsJsonObject().add("highlight", highlight);

        return gson.toJson(query);
    }

    private JsonElement parse(String query) {
        JsonElement subQuery = null;

        while(true) {
            BoolOperator lastOperator = BoolQueryTokenParser.getLastOperator(query);
            if (lastOperator == null) {
                List<String> tokens = new ArrayList<>();
                tokens.add(query);

                return generateJsonBoolElement(tokens, new BoolOperator(BoolOperator.Operator.AND, 0), subQuery);
            } else {
                subQuery = parseSameOperators(query, lastOperator, subQuery);
                BoolOperator differentOperator = BoolQueryTokenParser.getDifferentLastOperator(query, lastOperator);
                if(differentOperator == null) {
                    return subQuery;
                }
                query = BoolQueryTokenParser.getTokenBeforeOperator(query, differentOperator);
            }
        }
    }

    private JsonElement parseSameOperators(String query, BoolOperator lastOperator, JsonElement subQuery) {
        List<String> tokens = new ArrayList<>();

        while(true) {
            BoolOperator nextToLastOperator = BoolQueryTokenParser.getLastOperator(BoolQueryTokenParser.getTokenBeforeOperator(query, lastOperator));
            String lastToken = BoolQueryTokenParser.getTokenAfterOperator(query, lastOperator);

            if(nextToLastOperator == null) {
                String nextToLastToken = BoolQueryTokenParser.getTokenBeforeOperator(query, lastOperator);
                tokens.add(lastToken);
                tokens.add(nextToLastToken);

                return generateJsonBoolElement(tokens, lastOperator, subQuery);
            }else{
                tokens.add(lastToken);

                if(nextToLastOperator.equals(lastOperator)) {
                    query = BoolQueryTokenParser.getTokenBeforeOperator(query, lastOperator);
                    lastOperator = nextToLastOperator;
                }else{
                    String nextToLastToken = BoolQueryTokenParser.getTokenAfterOperator(BoolQueryTokenParser.getTokenBeforeOperator(query, lastOperator), nextToLastOperator);
                    tokens.add(nextToLastToken);

                    return generateJsonBoolElement(tokens, lastOperator, subQuery);
                }
            }
        }
    }

    private JsonElement generateJsonBoolElement(List<String> tokens, BoolOperator boolOperator, JsonElement subQuery) {
        JsonElement boolObject = new JsonObject();
        JsonElement operatorObject = new JsonObject();

        JsonArray operatorArray = generateJsonArrayFromTokens(tokens);
        if(subQuery != null) {
            operatorArray.add(subQuery);
        }

        if(boolOperator.getOperator() == BoolOperator.Operator.AND) {
            operatorObject.getAsJsonObject().add(ElasticSearchJsonConstants.TYPE_MARK_AND, operatorArray);
        }else{
            operatorObject.getAsJsonObject().add(ElasticSearchJsonConstants.TYPE_MARK_OR, operatorArray);
        }

        boolObject.getAsJsonObject().add(ElasticSearchJsonConstants.QUERY_ATTR_TYPE, operatorObject);

        return boolObject;
    }

    private JsonArray generateJsonArrayFromTokens(List<String> tokens) {
        JsonElement operatorArray = new JsonArray();
        for(String token: tokens) {
            String parameter = BoolQueryTokenParser.convertSerbianParameterNameToEsIndexName(
                    BoolQueryTokenParser.getTokenParameter(token));

            if(parameter == null) {
                continue;
            }

            String value = BoolQueryTokenParser.getTokenValue(token);
            JsonElement boolParameter = new JsonObject();

            JsonElement operator = new JsonObject();
            if(BoolQueryTokenParser.checkIfTokenValueIsPhrase(value)) {
                boolParameter.getAsJsonObject().addProperty(parameter, BoolQueryTokenParser.removeQuotationMarksFromPhrase(value));
                operator.getAsJsonObject().add(ElasticSearchJsonConstants.PHRASE_ATTR, boolParameter);
            }else{
                boolParameter.getAsJsonObject().addProperty(parameter, value);
                operator.getAsJsonObject().add(ElasticSearchJsonConstants.CONDITION_ATTR, boolParameter);
            }

            operatorArray.getAsJsonArray().add(operator);
        }

        return operatorArray.getAsJsonArray();
    }

}
