package com.payment.pay.controllers;

import com.payment.pay.models.Payment;
import com.payment.pay.models.PaymentConst;
import com.payment.pay.models.PaymentResult;
import com.payment.pay.services.PaymentRestClient;
import com.payment.pay.services.PaymentService;
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
    public String paymentResult(Model model, @RequestParam("Result") String result, @RequestParam("Sign") String sign, @RequestParam("Amount") String amount) {
        boolean signValid = paymentService.validateResponseSign(paymentConst.getMsTxnId(), amount, paymentConst.getCurrAlphaCode(), result, paymentConst.getKey(), paymentConst.getMid(), sign);
        model.addAttribute("paymentResult", new PaymentResult(amount, signValid));
        return "rurl";
    }

    @PostMapping("/payment")
    public Object paymentSubmit(@ModelAttribute Payment payment, Model model) {
        String amount = String.valueOf(payment.getAmount());

        paymentConst.setMsTxnId(paymentService.getPaymentUniqueId());
        //model.addAttribute("payment", payment);
        ResponseEntity<String> responseLicenseCheck = paymentRestClient.getPaymentRestClient().post()
                .uri("pay_gate/paygt")
                .body(paymentService.getPaymentBody(paymentConst, amount))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .retrieve()
                .toEntity(String.class);
        return new ResponseEntity<>(responseLicenseCheck.getBody(), responseLicenseCheck.getStatusCode());
    }
}
