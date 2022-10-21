package com.easytobuy.external.clients;

import com.easytobuy.external.model.PaymentRequest;
import com.easytobuy.external.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE/api/v1/products")
public interface ProductService {
    @PutMapping("/reduceQuantity/{productId}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("productId") String productId, @RequestParam long quantity);
}
