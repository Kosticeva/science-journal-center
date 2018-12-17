package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.enums.ReviewSummary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO {

    private Long id;

    private Long task;

    private String publicComment;

    private String privateComment;

    private ReviewSummary summary;

    private LocalDateTime timestamp;

    public CommentDTO() {
    }

    public CommentDTO(Long id, Long task, String publicComment, String privateComment,
                      ReviewSummary summary, LocalDateTime timestamp) {
        this.id = id;
        this.task = task;
        this.publicComment = publicComment;
        this.privateComment = privateComment;
        this.summary = summary;
        this.timestamp = timestamp;
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
                summary == that.summary &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, publicComment, privateComment, summary, timestamp);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", task=" + task +
                ", publicComment='" + publicComment + '\'' +
                ", privateComment='" + privateComment + '\'' +
                ", summary=" + summary +
                ", timestamp=" + timestamp +
                '}';
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
