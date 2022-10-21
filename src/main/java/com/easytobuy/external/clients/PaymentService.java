package com.easytobuy.external.clients;

import com.easytobuy.external.model.PaymentRequest;
import com.easytobuy.external.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE/api/v1/payment")
public interface PaymentService {
    @PostMapping
    ResponseEntity<PaymentResponse> doPayment(@RequestBody PaymentRequest paymentRequest);
}
