package com.uns.ftn.sciencejournal.model;

import java.util.List;
import java.util.Objects;

public class SearchQuery {

    private List<SearchFieldQuery> queriesByFields;
    private String operatorsBetweenFields;
    private int size;

    public SearchQuery() {
    }

    public SearchQuery(List<SearchFieldQuery> queriesByFields, String operatorsBetweenFields, int size) {
        this.queriesByFields = queriesByFields;
        this.operatorsBetweenFields = operatorsBetweenFields;
        this.size = size;
    }

    public List<SearchFieldQuery> getQueriesByFields() {
        return queriesByFields;
    }

    public void setQueriesByFields(List<SearchFieldQuery> queriesByFields) {
        this.queriesByFields = queriesByFields;
    }

    public String getOperatorsBetweenFields() {
        return operatorsBetweenFields;
    }

    public void setOperatorsBetweenFields(String operatorsBetweenFields) {
        this.operatorsBetweenFields = operatorsBetweenFields;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchQuery that = (SearchQuery) o;
        return Objects.equals(queriesByFields, that.queriesByFields) &&
                Objects.equals(operatorsBetweenFields, that.operatorsBetweenFields) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queriesByFields, operatorsBetweenFields, size);
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "queriesByFields=" + queriesByFields +
                ", operatorsBetweenFields='" + operatorsBetweenFields + '\'' +
                ", size=" + size +
                '}';
    }
}
