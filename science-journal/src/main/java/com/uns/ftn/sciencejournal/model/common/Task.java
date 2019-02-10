package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ASSIGNEE")
    private Credentials user;

    @Column(name = "DEADLINE", nullable = false)
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "ID")})
    private Application paper;

    @Column(name = "SUMMARY", nullable = false)
    private String summary;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState type;

    @Column(name = "FINISHED", nullable = false)
    private Boolean finished;

    public Task() {
    }

    public Task(Credentials user, LocalDateTime deadline, Application paper, String summary,
                PaperApplicationState type, Boolean finished) {
        this.user = user;
        this.deadline = deadline;
        this.paper = paper;
        this.summary = summary;
        this.type = type;
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(user, task.user) &&
                Objects.equals(deadline, task.deadline) &&
                Objects.equals(paper, task.paper) &&
                Objects.equals(summary, task.summary) &&
                type == task.type &&
                Objects.equals(finished, task.finished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, deadline, paper, summary, type, finished);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", deadline=" + deadline +
                ", paper=" + paper +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", finished=" + finished +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Application getPaper() {
        return paper;
    }

    public void setPaper(Application paper) {
        this.paper = paper;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PaperApplicationState getType() {
        return type;
    }

    public void setType(PaperApplicationState type) {
        this.type = type;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
