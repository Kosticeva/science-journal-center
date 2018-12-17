package com.uns.ftn.sciencejournal.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDTO {

    private Long merchantOrderId;

    private LocalDateTime merchantTimestamp;

    private String payerId;

    private String merchantId;

    private Double amount;

    public OrderDTO(Long merchantOrderId, LocalDateTime merchantTimestamp, String payerId, String merchantId, Double amount) {
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.payerId = payerId;
        this.merchantId = merchantId;
        this.amount = amount;
    }

    public OrderDTO() {
    }

    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public LocalDateTime getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(merchantOrderId, orderDTO.merchantOrderId) &&
                Objects.equals(merchantTimestamp, orderDTO.merchantTimestamp) &&
                Objects.equals(payerId, orderDTO.payerId) &&
                Objects.equals(merchantId, orderDTO.merchantId) &&
                Objects.equals(amount, orderDTO.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantOrderId, merchantTimestamp, payerId, merchantId, amount);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "merchantOrderId=" + merchantOrderId +
                ", merchantTimestamp=" + merchantTimestamp +
                ", payerId='" + payerId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
