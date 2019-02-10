package com.uns.ftn.sciencejournal.dto;

public class PaperResultDTO {

    private String doi;
    private String title;
    private String highlight;
    private Double price;
    private String currency;
    private String linkForPurchase;
    private String issue;
    private String author;

    public PaperResultDTO() {
    }

    public PaperResultDTO(String doi, String title, String highlight, Double price, String linkForPurchase, String issue, String author, String currency) {
        this.doi = doi;
        this.title = title;
        this.highlight = highlight;
        this.price = price;
        this.linkForPurchase = linkForPurchase;
        this.issue = issue;
        this.author = author;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLinkForPurchase() {
        return linkForPurchase;
    }

    public void setLinkForPurchase(String linkForPurchase) {
        this.linkForPurchase = linkForPurchase;
    }
}
