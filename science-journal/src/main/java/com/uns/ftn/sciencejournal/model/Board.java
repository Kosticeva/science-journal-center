package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @Embeddable
    public class BoardPK implements Serializable{

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
    }
}
