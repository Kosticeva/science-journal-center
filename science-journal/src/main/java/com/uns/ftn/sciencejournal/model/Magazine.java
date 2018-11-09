package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="MAGAZINE")
public class Magazine {

    @Id
    @Column(name="ISSN", length = 8)
    private String issn;

    @Column(name="NAME", length = 127, nullable = false)
    private String name;

    @Column(name="TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private MagazinePaymentType type;

    @Column(name="PRICE", nullable = false)
    private Double price;

    @Column(name="MEMBERSHIP", nullable = true)
    private Double membership;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD")
    private BoardOfEditors board;

    public Magazine() {
    }

    public Magazine(String issn, String name, MagazinePaymentType type, Double price, Double membership, BoardOfEditors board) {
        this.issn = issn;
        this.name = name;
        this.type = type;
        this.price = price;
        this.membership = membership;
        this.board = board;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MagazinePaymentType getType() {
        return type;
    }

    public void setType(MagazinePaymentType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMembership() {
        return membership;
    }

    public void setMembership(Double membership) {
        this.membership = membership;
    }

    public BoardOfEditors getBoard() {
        return board;
    }

    public void setBoard(BoardOfEditors board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(issn, magazine.issn) &&
                Objects.equals(name, magazine.name) &&
                type == magazine.type &&
                Objects.equals(price, magazine.price) &&
                Objects.equals(membership, magazine.membership) &&
                Objects.equals(board, magazine.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issn, name, type, price, membership, board);
    }
}
