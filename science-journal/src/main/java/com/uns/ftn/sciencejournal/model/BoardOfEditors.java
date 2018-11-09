package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="BOARD")
public class BoardOfEditors {

    @Id
    @Column(name="ID", length = 8)
    private String boardId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_EDITOR")
    private Editor mainEditor;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOARD_EDITORS", joinColumns = @JoinColumn(name = "BOARD_ID"), inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    private Set<Editor> editors = new HashSet<>();

    public BoardOfEditors() { }

    public BoardOfEditors(String boardId, Editor mainEditor, Set<Editor> editors) {
        this.boardId = boardId;
        this.mainEditor = mainEditor;
        this.editors = editors;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public Editor getMainEditor() {
        return mainEditor;
    }

    public void setMainEditor(Editor mainEditor) {
        this.mainEditor = mainEditor;
    }

    public Set<Editor> getEditors() {
        return editors;
    }

    public void setEditors(Set<Editor> editors) {
        this.editors = editors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardOfEditors that = (BoardOfEditors) o;
        return Objects.equals(boardId, that.boardId) &&
                Objects.equals(mainEditor, that.mainEditor) &&
                Objects.equals(editors, that.editors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, mainEditor, editors);
    }
}
