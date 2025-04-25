package com.payment.pay.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment")
public class PaymentConst {
    private String mid;
    private String eshopId;
    private String key;
    private String rurl;
    private String currAlphaCode;
    private String msTxnId;
    private String clientId;
    private String email;
    private String firstName;
    private String familyName;
    private String timestamp;
    private String redirectSign;
    private String debug;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getEshopId() {
        return eshopId;
    }

    public void setEshopId(String eshopId) {
        this.eshopId = eshopId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
    }

    public String getCurrAlphaCode() {
        return currAlphaCode;
    }

    public void setCurrAlphaCode(String currAlphaCode) {
        this.currAlphaCode = currAlphaCode;
    }

    public String getMsTxnId() {
        return msTxnId;
    }

    public void setMsTxnId(String msTxnId) {
        this.msTxnId = msTxnId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRedirectSign() {
        return redirectSign;
    }

    public void setRedirectSign(String redirectSign) {
        this.redirectSign = redirectSign;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }
}
