package com.payment.pay.controllers;

import com.payment.pay.models.Payment;
import com.payment.pay.models.PaymentConst;
import com.payment.pay.models.PaymentResult;
import com.payment.pay.services.PaymentRestClient;
import com.payment.pay.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PaymentController {

    private final PaymentConst paymentConst;
    private final PaymentRestClient paymentRestClient;
    private final PaymentService paymentService;

    public PaymentController(PaymentConst paymentConst, PaymentRestClient paymentRestClient, PaymentService paymentService) {
        this.paymentConst = paymentConst;
        this.paymentRestClient = paymentRestClient;
        this.paymentService = paymentService;
    }

    @GetMapping("/request")
    public String requestForm(Model model) {
        model.addAttribute("payment", new Payment());
        return "request";
    }

    @GetMapping("/rurl")
    public String paymentResult(Model model, @RequestParam("Result") String result, @RequestParam("Sign") String sign, @RequestParam("Amount") String amount, @RequestParam("CurrCode") String currency) {
        boolean signValid = paymentService.validateResponseSign(paymentConst.getMsTxnId(), amount, paymentConst.getCurrAlphaCode(), result, paymentConst.getKey(), paymentConst.getMid(), sign);
        model.addAttribute("paymentResult", new PaymentResult(amount, signValid, paymentConst.getMsTxnId(), result, currency));
        return "rurl";
    }

    @PostMapping("/payment")
    public Object paymentSubmit(@ModelAttribute Payment payment) {
        if (payment.getAmount() == null || payment.getAmount() < 1) {
            return new ResponseEntity<>("Neplatná hodnota.", HttpStatus.BAD_REQUEST);
        }
        paymentConst.setTimestamp(paymentService.getPaymentTimestamp());
        paymentConst.setMsTxnId(paymentService.getPaymentUniqueId());
        ResponseEntity<String> responseLicenseCheck = paymentRestClient.getPaymentRestClient().post()
                .uri("pay_gate/paygt")
                .body(paymentService.getPaymentBody(paymentConst, payment.getAmountFormated()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .toEntity(String.class);
        return new ResponseEntity<>(responseLicenseCheck.getBody(), responseLicenseCheck.getStatusCode());
    }
}
