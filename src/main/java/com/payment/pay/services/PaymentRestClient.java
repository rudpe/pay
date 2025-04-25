package com.payment.pay.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentRestClient {

    @Value("${basePaymentUrl}")
    private String basePaymentUrl;

    public RestClient getPaymentRestClient() {
        return RestClient.builder()
                .baseUrl(basePaymentUrl)
                .build();
    }
}
