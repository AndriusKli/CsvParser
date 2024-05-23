package org.andriusk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionDetails {
    @JsonProperty(value = "transaction_id")
    private String transactionId;
    @JsonProperty(value = "customer_id")
    private String customerId;
    @JsonProperty(value = "item_id")
    private String itemId;
    @JsonProperty(value = "transaction_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;
    @JsonProperty(value = "item_price")
    private Double itemPrice;

    @JsonProperty(value = "item_quantity")
    private Integer itemQuantity;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDetails that = (TransactionDetails) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(customerId,
                that.customerId) && Objects.equals(itemId, that.itemId) && Objects.equals(
                transactionDate, that.transactionDate) && Objects.equals(itemPrice,
                that.itemPrice) && Objects.equals(itemQuantity, that.itemQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, customerId, itemId, transactionDate, itemPrice, itemQuantity);
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "transactionId='" + transactionId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", transactionDate=" + transactionDate +
                ", itemPrice=" + itemPrice +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
