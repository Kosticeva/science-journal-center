package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.enums.ReviewSummary;

import java.util.Objects;

public class CommentDTO {

    private Long id;

    private Long task;

    private String publicComment;

    private String privateComment;

    private ReviewSummary summary;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Long task, String publicComment, String privateComment, ReviewSummary summary) {
        this.id = id;
        this.task = task;
        this.publicComment = publicComment;
        this.privateComment = privateComment;
        this.summary = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(task, that.task) &&
                Objects.equals(publicComment, that.publicComment) &&
                Objects.equals(privateComment, that.privateComment) &&
                summary == that.summary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, publicComment, privateComment, summary);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }

    public String getPublicComment() {
        return publicComment;
    }

    public void setPublicComment(String publicComment) {
        this.publicComment = publicComment;
    }

    public String getPrivateComment() {
        return privateComment;
    }

    public void setPrivateComment(String privateComment) {
        this.privateComment = privateComment;
    }

    public ReviewSummary getSummary() {
        return summary;
    }

    public void setSummary(ReviewSummary summary) {
        this.summary = summary;
    }
}
