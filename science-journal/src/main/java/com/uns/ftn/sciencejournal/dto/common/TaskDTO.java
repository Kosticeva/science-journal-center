package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskDTO {

    private Long id;

    private String user;

    private LocalDateTime deadline;

    private Long application;

    private String summary;

    private PaperApplicationState type;

    private Boolean finished;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String user, LocalDateTime deadline, Long application,
                   String summary, PaperApplicationState type, Boolean finished) {
        this.id = id;
        this.user = user;
        this.deadline = deadline;
        this.application = application;
        this.summary = summary;
        this.type = type;
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Long getApplication() {
        return application;
    }

    public void setApplication(Long application) {
        this.application = application;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) &&
                Objects.equals(user, taskDTO.user) &&
                Objects.equals(deadline, taskDTO.deadline) &&
                Objects.equals(application, taskDTO.application) &&
                Objects.equals(summary, taskDTO.summary) &&
                type == taskDTO.type &&
                Objects.equals(finished, taskDTO.finished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, deadline, application, summary, type, finished);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", deadline=" + deadline +
                ", application=" + application +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", finished=" + finished +
                '}';
    }
}