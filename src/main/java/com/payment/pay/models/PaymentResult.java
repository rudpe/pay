package com.payment.pay.models;

public class PaymentResult {
    private String amount;
    private Boolean signValid;

    public PaymentResult(String amount, Boolean signValid) {
        this.amount = amount;
        this.signValid = signValid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getSignValid() {
        return signValid;
    }

    public void setSignValid(Boolean signValid) {
        this.signValid = signValid;
    }
}
