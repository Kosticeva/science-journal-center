package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.ReviewSummary;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "TASK")
    private Task task;

    @Column(name = "PUBLIC_COMMENT", length = 1023)
    private String publicComment;

    @Column(name = "PRIVATE_COMMENT", length = 1023)
    private String privateComment;

    @Column(name = "REVIEW_SUMMARY")
    @Enumerated(EnumType.STRING)
    private ReviewSummary summary;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    public Comment() {
    }

    public Comment(Long id, Task task, String publicComment, String privateComment,
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(task, comment.task) &&
                Objects.equals(publicComment, comment.publicComment) &&
                Objects.equals(privateComment, comment.privateComment) &&
                summary == comment.summary &&
                Objects.equals(timestamp, comment.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, publicComment, privateComment, summary, timestamp);
    }

    @Override
    public String toString() {
        return "Comment{" +
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
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
