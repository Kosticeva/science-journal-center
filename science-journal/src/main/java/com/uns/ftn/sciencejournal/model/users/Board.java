package com.uns.ftn.sciencejournal.model.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "BOARD")
public class Board implements Serializable {

    @EmbeddedId
    private BoardPK key;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("issn")
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("code")
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EDITOR")
    private Editor editor;

    public Board() {
    }

    public Board(BoardPK key, Magazine magazine, ScienceField field, Editor editor) {
        this.key = key;
        this.magazine = magazine;
        this.field = field;
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "Board{" +
                "key=" + key +
                ", magazine=" + magazine +
                ", field=" + field +
                ", editor=" + editor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(key, board.key) &&
                Objects.equals(magazine, board.magazine) &&
                Objects.equals(field, board.field) &&
                Objects.equals(editor, board.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, magazine, field, editor);
    }

    public BoardPK getKey() {
        return key;
    }

    public void setKey(BoardPK key) {
        this.key = key;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public ScienceField getField() {
        return field;
    }

    public void setField(ScienceField field) {
        this.field = field;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    @Embeddable
    public class BoardPK implements Serializable {

        protected String issn;
        protected String code;

        public BoardPK(String issn, String code) {
            this.issn = issn;
            this.code = code;
        }

        public BoardPK() {
        }

        public String getIssn() {
            return issn;
        }

        public void setIssn(String issn) {
            this.issn = issn;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "BoardPK{" +
                    "issn='" + issn + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BoardPK boardPK = (BoardPK) o;
            return Objects.equals(issn, boardPK.issn) &&
                    Objects.equals(code, boardPK.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(issn, code);
        }
    }
}
