package com.uns.ftn.sciencejournal.service.utils;

public class FormDTO {

    private String id;
    private String label;
    private Object value;
    private String type;

    public FormDTO() {
    }

    public FormDTO(String id, String label, Object value, String type) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
