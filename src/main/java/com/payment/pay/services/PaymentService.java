package com.payment.pay.services;

import com.payment.pay.models.PaymentConst;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Random;

@Component
public class PaymentService {

    private final SignService signService;

    public PaymentService(SignService signService) {
        this.signService = signService;
    }

    public boolean validateResponseSign(String msTxnId, String amount, String currencyAlphaCode, String result, String key, String mid, String responseSign) {
        String localSign = generateResponseSign(msTxnId, amount, currencyAlphaCode, result, key, mid);
        return localSign.equalsIgnoreCase(responseSign);
    }

    public String getPaymentUniqueId() {
        return String.valueOf(new Random().nextInt(10000));
    }

    public MultiValueMap<String, String> getPaymentBody(PaymentConst paymentConst, String amount) {
        MultiValueMap<String, String> bodyPair = new LinkedMultiValueMap<>();
        String sign = signService.generateRequestSign(paymentConst.getMid(),
                amount,
                paymentConst.getCurrAlphaCode(),
                paymentConst.getMsTxnId() ,
                paymentConst.getFirstName(),
                paymentConst.getFamilyName(),
                paymentConst.getTimestamp(),
                paymentConst.getKey());
        bodyPair.add("Mid", paymentConst.getMid());
        bodyPair.add("EshopId", paymentConst.getEshopId());
        bodyPair.add("Key", paymentConst.getKey());
        bodyPair.add("Amount", amount);
        bodyPair.add("CurrAlphaCode", paymentConst.getCurrAlphaCode());
        bodyPair.add("RURL", paymentConst.getRurl());
        bodyPair.add("Debug", paymentConst.getDebug());
        bodyPair.add("MsTxnId", paymentConst.getMsTxnId());
        bodyPair.add("ClientId", paymentConst.getClientId());
        bodyPair.add("Email", paymentConst.getEmail());
        bodyPair.add("FirstName", paymentConst.getFirstName());
        bodyPair.add("FamilyName", paymentConst.getFamilyName());
        bodyPair.add("Timestamp", paymentConst.getTimestamp());
        bodyPair.add("RedirectSign", paymentConst.getRedirectSign());
        bodyPair.add("Sign", sign);
        return bodyPair;
    }

    private String generateResponseSign(String msTxnId, String amount, String currencyAlphaCode, String result, String key, String mid) {
        return signService.generateResponseSign(msTxnId, amount, currencyAlphaCode, result, key, mid);
    }
}
