package com.uns.ftn.sciencejournal.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;

@Service
public class ElasticSearchPlugin {

    private RestTemplate template;
    private static final String ELASTIC_SEARCH_URI = "http://localhost:9200/science-journal/%s/_search";
    private static final String ELASTIC_INDEX_URI = "http://localhost:9200/science-journal/%s/";
    private static final String PAPER_INDEX = "papers";
    private static final String REVIEWER_INDEX = "reviewers";

    @Autowired
    public ElasticSearchPlugin() {
        template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    public String searchForPapers(String jsonBody) {
        return searchFor(PAPER_INDEX , jsonBody);
    }

    public String searchForReviewers(String jsonBody) {
        return searchFor(REVIEWER_INDEX, jsonBody);
    }

    public String addPaper(String jsonBody, String id) {
        return addToIndex(PAPER_INDEX , jsonBody, id);
    }

    public String addReviewer(String jsonBody, String id) {
        return addToIndex(REVIEWER_INDEX, jsonBody, id);
    }

    public String removePaper(String id){
        return removeFromIndex(PAPER_INDEX , id);
    }

    public String removeReviewer(String id) {
        return removeFromIndex(REVIEWER_INDEX, id);
    }

    public String searchFor(String index, String jsonBody) {
        return sendRequestToElasticSearch(ELASTIC_SEARCH_URI, index,"", jsonBody, HttpMethod.POST);
    }

    public String addToIndex(String index, String jsonBody, String id){
        return sendRequestToElasticSearch(ELASTIC_INDEX_URI, index, id, jsonBody, HttpMethod.PUT);
    }

    public String removeFromIndex(String index, String id){
        return sendRequestToElasticSearch(ELASTIC_INDEX_URI, index, id, "", HttpMethod.DELETE);
    }

    private String sendRequestToElasticSearch(String uri, String index, String id, String body, HttpMethod method){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> result = template.exchange(
                    String.format(uri, index) + URLEncoder.encode(id, "UTF-8"), method, entity, String.class, new HashMap<>());

            return result.getBody();
        }catch (UnsupportedEncodingException ex){
            ex.printStackTrace();
            return "";
        }
    }
}
