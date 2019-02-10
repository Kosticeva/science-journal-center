package com.uns.ftn.sciencejournal.model;

import java.util.List;

public class PaperSearchModel {

    private PaperMagazineSearchModel magazine;
    private String doi;
    private String title;
    private String field;
    private String content;
    private List<String> keywords;
    private List<PaperAuthorSearchModel> authors;
    private List<PaperReviewerSearchModel> reviewers;

    public PaperSearchModel() {}

    public PaperSearchModel(PaperMagazineSearchModel magazine, String doi, String title, String field,
                            String content, List<String> keywords, List<PaperAuthorSearchModel> authors,
                            List<PaperReviewerSearchModel> reviewers) {
        this.magazine = magazine;
        this.doi = doi;
        this.title = title;
        this.field = field;
        this.content = content;
        this.keywords = keywords;
        this.authors = authors;
        this.reviewers = reviewers;
    }

    public PaperMagazineSearchModel getMagazine() {
        return magazine;
    }

    public void setMagazine(PaperMagazineSearchModel magazine) {
        this.magazine = magazine;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<PaperAuthorSearchModel> getAuthors() {
        return authors;
    }

    public void setAuthors(List<PaperAuthorSearchModel> authors) {
        this.authors = authors;
    }

    public List<PaperReviewerSearchModel> getReviewers() {
        return reviewers;
    }

    public void setReviewers(List<PaperReviewerSearchModel> reviewers) {
        this.reviewers = reviewers;
    }

    public class PaperMagazineSearchModel{
        private String issn;
        private String title;

        public  PaperMagazineSearchModel () {}

        public PaperMagazineSearchModel(String issn, String title) {
            this.issn = issn;
            this.title = title;
        }

        public String getIssn() {
            return issn;
        }

        public void setIssn(String issn) {
            this.issn = issn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class PaperAuthorSearchModel{
        private String firstName;
        private String lastName;
        private Long id;
        private Location location;

        public PaperAuthorSearchModel() {}

        public PaperAuthorSearchModel(String firstName, String lastName, Long id, Location location) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.id = id;
            this.location = location;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    public class PaperReviewerSearchModel {
        private String username;
        private Location location;

        public PaperReviewerSearchModel() {}

        public PaperReviewerSearchModel(String username, Location location) {
            this.username = username;
            this.location = location;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    public class Location {
        private String lat;
        private String lon;

        public Location() {}

        public Location(String lat, String lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}
