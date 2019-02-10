package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.Fields;

import java.util.Objects;

public class SearchFieldQuery {

    private String term;
    private Fields field;

    public SearchFieldQuery() {
    }

    public SearchFieldQuery(String term, Fields field) {
        this.term = term;
        this.field = field;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchFieldQuery query = (SearchFieldQuery) o;
        return Objects.equals(term, query.term) &&
                field == query.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, field);
    }

    @Override
    public String toString() {
        return "SearchFieldQuery{" +
                "term='" + term + '\'' +
                ", field=" + field +
                '}';
    }
}
