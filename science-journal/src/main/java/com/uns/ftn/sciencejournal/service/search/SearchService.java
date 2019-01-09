package com.uns.ftn.sciencejournal.service.search;

import com.uns.ftn.sciencejournal.model.SearchQuery;
import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.common.ScienceField;
import com.uns.ftn.sciencejournal.model.enums.SearchType;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.service.utils.ElasticSearchJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    ElasticSearchPlugin elasticSearchPlugin;

    @Autowired
    PaperRepository paperRepository;

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
        if(query.getResultCount() == 0){
            query.setResultCount(2);
        }
        json.append(jsonUtil.generateSize(query.getResultCount()));
        json.append("}");
        System.out.println(json.toString());
        String response = elasticSearchPlugin.searchFor(json.toString());
        System.out.println(response);
        return mapObjectToPaper(jsonUtil.getHitsFromJson(response));
    }

    private List<Paper> mapObjectToPaper(List<Object> objects){
        List<Paper> finalList = new ArrayList<>();
        //JsonParser parser = JsonParserFactory.getJsonParser();
        for(Object jsonNode: objects){
            Paper paper = new Paper();
            Map<String, Object> source = (Map<String, Object>) ((Map<String, Object>)jsonNode).get("_source");
            paper.setDoi(source.get("doi").toString());
            paper.setTitle(source.get("title").toString());
            List<Object> keywords = JsonParserFactory.getJsonParser().parseList(
                    source.get("keywords").toString()
                            .replace("[", "[\"")
                            .replace("]", "\"]")
                            .replace(",", "\",\""));
            StringBuilder builder = new StringBuilder();
            for(Object word: keywords){
                builder.append(word.toString() + ",");
            }

            paper.setKeyTerms(builder.toString());
            finalList.add(paper);
        }

        return finalList;
    }

    private Map<String, String> generateParamMap(SearchQuery query) {
        Map<String, String> queries = new HashMap<>();

        if (query.getAuthorFName() != null && !query.getAuthorFName().equals("")) {
            queries.put("authors.first_name", query.getAuthorFName());
        }

        if (query.getAuthorLName() != null && !query.getAuthorLName().equals("")) {
            queries.put("authors.last_name", query.getAuthorLName());
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
            queries.put("magazine.title", query.getMagazine());
        }

        if (query.getPaper() != null && !query.getPaper().equals("")) {
            queries.put("title", query.getPaper());
        }

        if (query.getScienceFields() != null && query.getScienceFields().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (String field : query.getScienceFields()) {
                builder.append(field);
                builder.append(" ");
            }
            queries.put("science_field", builder.toString());
        }

        return queries;
    }
}
