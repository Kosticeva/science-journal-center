package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.MerchandiseType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long id;

    @Column(name = "MERCHANDISE_ID", nullable = false)
    private String merchandiseId;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDate timestamp;

    @Column(name = "AMMOUNT", nullable = false)
    private Integer ammount;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private MerchandiseType type;

    public Transaction() {
    }

    public Transaction(String merchandiseId, LocalDate timestamp, Integer ammount, Double total, MerchandiseType type) {
        this.merchandiseId = merchandiseId;
        this.timestamp = timestamp;
        this.ammount = ammount;
        this.total = total;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(String merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAmmount() {
        return ammount;
    }

    public void setAmmount(Integer ammount) {
        this.ammount = ammount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public MerchandiseType getType() {
        return type;
    }

    public void setType(MerchandiseType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchandiseId, that.merchandiseId) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(ammount, that.ammount) &&
                Objects.equals(total, that.total) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchandiseId, timestamp, ammount, total, type);
    }
}
