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
    private static String ELASTIC_SEARCH_URI = "http://localhost:9200/science-journal/papers/_search";
    private static String ELASTIC_INDEX_URI = "http://localhost:9200/science-journal/papers/";

    @Autowired
    public ElasticSearchPlugin(){
        template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

    public String searchFor(String jsonBody) {
        return sendRequestToElasticSearch(ELASTIC_SEARCH_URI, "", jsonBody, HttpMethod.POST);
    }

    public String addToIndex(String jsonBody, String id){
        return sendRequestToElasticSearch(ELASTIC_INDEX_URI, id, jsonBody, HttpMethod.PUT);
    }

    public String removeFromIndex(String id){
        return sendRequestToElasticSearch(ELASTIC_INDEX_URI, id, "", HttpMethod.DELETE);
    }

    private String sendRequestToElasticSearch(String uri, String id, String body, HttpMethod method){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> result = template.exchange(
                    uri + URLEncoder.encode(id, "UTF-8"), method, entity, String.class, new HashMap<>());

            return result.getBody();
        }catch (UnsupportedEncodingException ex){
            ex.printStackTrace();
            return "";
        }
    }
}
