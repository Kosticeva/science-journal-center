package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.SearchType;

import java.util.List;

public class SearchQuery {

    private String magazine;

    private String paper;

    private String authorFName;

    private String authorLName;

    private List<String> keyWords;

    private List<String> scienceFields;

    private SearchType type;

    private int resultCount;

    public SearchQuery() {
    }

    public SearchQuery(String magazine, String paper, String authorFName, String authorLName,
                       List<String> keyWords, List<String> scienceFields, SearchType type, int resultCount) {
        this.magazine = magazine;
        this.paper = paper;
        this.authorFName = authorFName;
        this.authorLName = authorLName;
        this.keyWords = keyWords;
        this.scienceFields = scienceFields;
        this.type = type;
        this.resultCount = resultCount;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getAuthorFName() {
        return authorFName;
    }

    public void setAuthorFName(String authorFName) {
        this.authorFName = authorFName;
    }

    public String getAuthorLName() {
        return authorLName;
    }

    public void setAuthorLName(String authorLName) {
        this.authorLName = authorLName;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getScienceFields() {
        return scienceFields;
    }

    public void setScienceFields(List<String> scienceFields) {
        this.scienceFields = scienceFields;
    }

    public SearchType getType() {
        return type;
    }

    public void setType(SearchType type) {
        this.type = type;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "magazine='" + magazine + '\'' +
                ", paper='" + paper + '\'' +
                ", authorFName='" + authorFName + '\'' +
                ", authorLName='" + authorLName + '\'' +
                ", keyWords=" + keyWords +
                ", scienceFields=" + scienceFields +
                ", type=" + type +
                ", count=" + resultCount +
                '}';
    }
}
