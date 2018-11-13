package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class BoardDTO {

    private String magazine;

    private String fieldCode;

    private Integer editor;

    public BoardDTO() {
    }

    public BoardDTO(String magazine, String fieldCode, Integer editor) {
        this.magazine = magazine;
        this.fieldCode = fieldCode;
        this.editor = editor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDTO boardDTO = (BoardDTO) o;
        return Objects.equals(magazine, boardDTO.magazine) &&
                Objects.equals(fieldCode, boardDTO.fieldCode) &&
                Objects.equals(editor, boardDTO.editor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazine, fieldCode, editor);
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getEditor() {
        return editor;
    }

    public void setEditor(Integer editor) {
        this.editor = editor;
    }
}
