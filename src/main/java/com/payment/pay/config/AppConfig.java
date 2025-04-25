package com.payment.pay.config;

import com.payment.pay.models.PaymentConst;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PaymentConst.class)
public class AppConfig {
}
