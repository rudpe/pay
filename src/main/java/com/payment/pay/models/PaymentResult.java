package com.payment.pay.models;

public class PaymentResult {
    private String amount;
    private Boolean signValid;
    private String number;
    private String state;

    public PaymentResult(String amount, Boolean signValid, String number, String state) {
        this.amount = amount;
        this.signValid = signValid;
        this.number = number;
        this.state = state;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
