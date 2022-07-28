package com.tw.bootcamp.bookshop.money;
c
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Money {
    private String currency;
    @Column(columnDefinition = "NUMERIC")
    private Double amount;
    public static Money rupees(double amount) {
        return new Money("INR", amount);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
