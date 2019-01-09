package com.uns.ftn.sciencejournal.service.search;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ElasticSearchPlugin {

    private RestTemplate template = new RestTemplate();
    private static String ELASTIC_SEARCH_URI = "http://localhost:9200/science-journal/papers/_search";
    private static String ELASTIC_INDEX_URI = "http://localhost:9200/science-journal/papers/";

    public String searchFor(String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> result = template.exchange(
                ELASTIC_SEARCH_URI, HttpMethod.POST, entity, String.class, new HashMap<>());

        return result.getBody();
    }

    public String addToIndex(String jsonBody, String id){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> result = template.exchange(
                ELASTIC_INDEX_URI + id, HttpMethod.PUT, entity, String.class, new HashMap<>());

        return result.getBody();
    }

    public String removeFromIndex(String id){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<String> result = template.exchange(
                ELASTIC_INDEX_URI + id, HttpMethod.DELETE, entity, String.class, new HashMap<>());

        return result.getBody();
    }
}
