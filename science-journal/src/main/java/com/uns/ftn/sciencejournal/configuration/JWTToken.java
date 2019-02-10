package com.uns.ftn.sciencejournal.configuration;

public class JWTToken {

    private String value;

    public JWTToken(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
