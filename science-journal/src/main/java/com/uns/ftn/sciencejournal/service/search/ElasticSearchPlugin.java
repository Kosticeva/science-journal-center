package com.uns.ftn.sciencejournal.service.search;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ElasticSearchPlugin {

    private RestTemplate template = new RestTemplate();

    public String searchFor(Map<String, String> andParams, Map<String, String> orParams, Map<String, String> phraseParams) {
        //return template.getForEntity();
        return null;
    }
}
