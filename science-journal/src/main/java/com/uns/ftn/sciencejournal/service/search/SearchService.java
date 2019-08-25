package com.uns.ftn.sciencejournal.service.search;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.uns.ftn.sciencejournal.dto.PaperResultDTO;
import com.uns.ftn.sciencejournal.model.SearchFieldQuery;
import com.uns.ftn.sciencejournal.model.SearchQuery;
import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.common.PaperApplication;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.Reviewer;
import com.uns.ftn.sciencejournal.repository.common.PaperRepository;
import com.uns.ftn.sciencejournal.repository.users.CredentialsRepository;
import com.uns.ftn.sciencejournal.repository.users.ReviewerRepository;
import com.uns.ftn.sciencejournal.service.utils.ElasticSearchJsonUtil;
import com.uns.ftn.sciencejournal.service.utils.OldElasticSearchJsonUtil;
import com.uns.ftn.sciencejournal.service.utils.PDFUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    ElasticSearchPlugin elasticSearchPlugin;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    public List<Credentials> searchReviewers(PaperApplication paperApplication) {
        OldElasticSearchJsonUtil jsonUtil = new OldElasticSearchJsonUtil();
        String similarPapers = elasticSearchPlugin.searchForPapers(jsonUtil.generateMoreLikeThisQuery(PDFUtils.readFromPDF(paperApplication.getFile()).replace("\n", "")));

        String reviewersOutside100kmRadius = elasticSearchPlugin.searchForReviewers(jsonUtil.generateGeoQuery(
                paperApplication.getAuthor().getUserDetails().getLatitude().toString(), paperApplication.getAuthor().getUserDetails().getLongitude().toString()));

        List<Reviewer> reviewersForMagazine = reviewerRepository.findByMagazinesAndFields(paperApplication.getMagazine(), paperApplication.getField());
        List<Credentials> allFoundUsers = new ArrayList<>(mapPaperToReviewer(jsonUtil.getHitsFromJson(similarPapers)));
        allFoundUsers.addAll(mapObjectToReviewer(jsonUtil.getHitsFromJson(reviewersOutside100kmRadius)));

        return reduceThoseThatWorkForMagazineInField(reduceListOfUsers(allFoundUsers), reviewersForMagazine);
    }

    private List<Credentials> reduceThoseThatWorkForMagazineInField(List<Credentials> found, List<Reviewer> work) {
        List<Credentials> finalList = new ArrayList<>();
        for (Reviewer worker : work) {
            for (Credentials founder : found) {
                if (worker.getUser().getUsername().equals(founder.getUsername())) {
                    finalList.add(founder);
                }
            }
        }

        return finalList;
    }

    private List<Credentials> reduceListOfUsers(List<Credentials> users) {
        return users.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public List<PaperResultDTO> searchPapers(SearchQuery query) {

        if (query.getQueriesByFields().size() == 0) {
            return new ArrayList<>();
        }

        if (query.getQueriesByFields().size() != query.getOperatorsBetweenFields().length()) {
            return new ArrayList<>();
        }

        OldElasticSearchJsonUtil jsonUtil = new OldElasticSearchJsonUtil();
        String json = jsonUtil.generateQueries(collectMustParameters(query), collectShouldParameters(query), query.getSize());


        String response = elasticSearchPlugin.searchForPapers(json);
        return mapObjectToPaper(jsonUtil.getHitsFromJson(response));
    }

    public List<PaperResultDTO> searchPapersQuery(String query) {
        ElasticSearchJsonUtil jsonUtil = new ElasticSearchJsonUtil();
        String json = jsonUtil.generateJsonFromBoolQuery(query);

        String response = elasticSearchPlugin.searchForPapers(json);
        OldElasticSearchJsonUtil oldJsonUtil = new OldElasticSearchJsonUtil();
        return mapObjectToPaper(oldJsonUtil.getHitsFromJson(response));
    }

    private Map<String, String> collectMustParameters(SearchQuery query) {
        List<SearchFieldQuery> queries = new ArrayList<>();

        for (int i = 0; i < query.getOperatorsBetweenFields().length(); i++) {
            if (query.getOperatorsBetweenFields().charAt(i) == '0') {
                queries.add(query.getQueriesByFields().get(i));
            }
        }

        return mapParameterFieldsToMap(queries);
    }

    private Map<String, String> collectShouldParameters(SearchQuery query) {
        List<SearchFieldQuery> queries = new ArrayList<>();

        for (int i = 0; i < query.getOperatorsBetweenFields().length(); i++) {
            if (query.getOperatorsBetweenFields().charAt(i) == '1') {
                queries.add(query.getQueriesByFields().get(i));
            }
        }

        return mapParameterFieldsToMap(queries);
    }

    private Map<String, String> mapParameterFieldsToMap(List<SearchFieldQuery> queries) {
        Map<String, String> objects = new HashMap<>();

        for (SearchFieldQuery query : queries) {
            switch (query.getField()) {
                case NASLOV:
                    objects.put("title", query.getTerm());
                    break;
                case CASOPIS:
                    objects.put("magazine.title", query.getTerm());
                    break;
                case SADRZAJ:
                    objects.put("content", query.getTerm());
                    break;
                case KLJUCNE_RECI:
                    objects.put("keywords", query.getTerm());
                    break;
                case NAUCNA_OBLAST:
                    objects.put("field", query.getTerm());
                    break;
                case AUTOR_IME:
                    objects.put("authors.firstName", query.getTerm());
                    break;
                case AUTOR_PREZIME:
                    objects.put("authors.lastName", query.getTerm());
                    break;
            }
        }

        return objects;
    }

    private List<Credentials> mapObjectToReviewer(JsonArray objects) {
        List<Credentials> finalList = new ArrayList<>();
        for (JsonElement element : objects) {
            JsonObject hit = element.getAsJsonObject().get("_source").getAsJsonObject();
            finalList.add(credentialsRepository.getOne(hit.get("username").getAsString()));
        }

        return finalList;
    }

    private List<Credentials> mapPaperToReviewer(JsonArray objects) {
        List<Credentials> finalList = new ArrayList<>();
        for (JsonElement element : objects) {
            JsonObject hit = element.getAsJsonObject().get("_source").getAsJsonObject();
            Paper paper = paperRepository.getOne(hit.get("doi").getAsString());
            /*List<Task> tasks = taskServicer.getAllForApplicationAndState(paper.getLastRevision(), PaperApplicationState.REVIEW);

            for(Task task: tasks) {
                finalList.add(task.getUser());
            }*/
        }

        return finalList;
    }

    private List<PaperResultDTO> mapObjectToPaper(JsonArray objects) {
        List<PaperResultDTO> finalList = new ArrayList<>();
        for (JsonElement element : objects) {
            JsonObject hit = element.getAsJsonObject().get("_source").getAsJsonObject();
            Paper paper = paperRepository.getOne(hit.get("doi").getAsString());

            PaperResultDTO resultDTO = new PaperResultDTO();
            resultDTO.setDoi(paper.getDoi());
            resultDTO.setTitle(paper.getTitle());
            resultDTO.setIssue(String.format("%s (izdanje %s)", paper.getPaperIssue().getMagazine().getName(), paper.getPaperIssue().getEdition()));
            resultDTO.setPrice(paper.getPrice());
            resultDTO.setCurrency(paper.getCurrency());
            try {
                resultDTO.setLinkForPurchase(String.format("http://localhost:8090/api/papers/download/%s", URLEncoder.encode(paper.getDoi(), "UTF-8")));
            } catch (Exception e) {
                resultDTO.setLinkForPurchase("");
            }

            JsonArray authors = hit.get("authors").getAsJsonArray();
            StringBuilder builder = new StringBuilder();

            for (JsonElement author : authors) {
                builder.append(author.getAsJsonObject().get("lastName").getAsString());
                builder.append(" ");
                builder.append(author.getAsJsonObject().get("firstName").getAsString());
                builder.append(", ");
            }

            resultDTO.setAuthor(builder.toString().substring(0, builder.toString().length() - 2));

            JsonElement test = element.getAsJsonObject().get("highlight");
            StringBuilder highlightBuilder = new StringBuilder();

            if (test == null) {
                String content = hit.get("content").getAsString();
                if (content.length() > 100) {
                    highlightBuilder.append(content.substring(0, 100));
                } else {
                    highlightBuilder.append(content);
                }
            } else {
                JsonArray highlights = test.getAsJsonObject().get("content").getAsJsonArray();
                if (highlights.get(0).getAsString().charAt(0) == highlights.get(0).getAsString().toLowerCase().charAt(0)) {
                    highlightBuilder.append(" ... ");
                }

                for (int i = 0; i < highlights.size(); i++) {
                    highlightBuilder.append(highlights.get(i).getAsString());
                    highlightBuilder.append(" ... ");
                }
            }

            resultDTO.setHighlight(highlightBuilder.toString());
            finalList.add(resultDTO);
        }

        return finalList;
    }
}
