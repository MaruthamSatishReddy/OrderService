package com.easytobuy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private String orderId;
  private String productId;
  private long quantity;
  private long amount;
  private PaymentMode paymentMode;
}
