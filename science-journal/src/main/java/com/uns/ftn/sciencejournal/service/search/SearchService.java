package com.uns.ftn.sciencejournal.service.search;

import com.uns.ftn.sciencejournal.model.SearchQuery;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.enums.SearchType;
import com.uns.ftn.sciencejournal.service.utils.ElasticSearchJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    ElasticSearchPlugin elasticSearchPlugin;

    public List<Paper> searchPapers(SearchQuery query) {
        ElasticSearchJsonUtil jsonUtil = new ElasticSearchJsonUtil();
        Map<String, String> params = generateParamMap(query);

        StringBuilder json = new StringBuilder("{\n");
        if (query.getType() == SearchType.AND) {
            json.append(jsonUtil.generateAndQuery(params));
        } else {
            json.append(jsonUtil.generateOrQuery(params));
        }

        json.append(",\n");
        json.append(jsonUtil.generateSize(query.getResultCount()));
        json.append("\n}");
        System.out.println(json.toString());
        return new ArrayList<>();
    }

    private Map<String, String> generateParamMap(SearchQuery query) {
        Map<String, String> queries = new HashMap<>();

        if (query.getAuthorFName() != null && !query.getAuthorFName().equals("")) {
            queries.put("author_fname", query.getAuthorFName());
        }

        if (query.getAuthorLName() != null && !query.getAuthorLName().equals("")) {
            queries.put("author_lname", query.getAuthorLName());
        }

        if (query.getKeyWords() != null && query.getKeyWords().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (String word : query.getKeyWords()) {
                builder.append(word);
                builder.append(" ");
            }
            queries.put("keywords", builder.toString());
        }

        if (query.getMagazine() != null && !query.getMagazine().equals("")) {
            queries.put("magazine", query.getMagazine());
        }

        if (query.getPaper() != null && !query.getPaper().equals("")) {
            queries.put("paper", query.getPaper());
        }

        if (query.getScienceFields() != null && query.getScienceFields().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (String field : query.getScienceFields()) {
                builder.append(field);
                builder.append(" ");
            }
            queries.put("fields", builder.toString());
        }

        return queries;
    }
}
