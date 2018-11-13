package com.uns.ftn.sciencejournal.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "SCIENCE_FIELD")
public class ScienceField {

    @Id
    @Column(name = "CODE", length = 3)
    private String code;

    @Column(name = "FIELD", nullable = false, length = 31)
    private String name;

    public ScienceField() {
    }

    public ScienceField(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScienceField that = (ScienceField) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "ScienceField{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
