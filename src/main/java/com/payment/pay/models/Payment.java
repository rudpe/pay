package com.payment.pay.models;

import java.text.DecimalFormat;

public class Payment {
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public String getAmountFormated() {
        return new DecimalFormat("#.00").format(amount);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
