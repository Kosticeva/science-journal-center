package com.uns.ftn.sciencejournal.service.utils;

public class ElasticSearchJsonConstants {

    public static final String QUERY_ATTR = "query";
    public static final String QUERY_ATTR_TYPE = "bool";

    public static final String PHRASE_ATTR = "match_phrase";
    public static final String CONDITION_ATTR = "match";
    public static final String CONDITION_ALL_ATTR = "match_all";

    public static final String TYPE_MARK_AND = "must";
    public static final String TYPE_MARK_OR = "should";

    public static final String SIZE_PARAM = "size";

    public static final String MORE_LIKE_THIS_ATTR = "mlt_field";
    public static final String LIKE_TEXT = "like_text";
    public static final String MIN_TERM_FREQ = "min_term_freq";
    public static final String MIN_DOC_FREQ = "min_doc_freq";

    public static final String FILTERED = "filtered";
    public static final String FILTER = "filter";
    public static final String GEO_DISTANCE = "geo_distance";
    public static final String DISTANCE = "distance";

    public static final String NEGATION = "not";
}
