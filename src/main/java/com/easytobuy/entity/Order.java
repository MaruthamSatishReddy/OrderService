package com.easytobuy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.Instant;

@Document(collection = "ORDER_DETAILS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String orderId;
    private String productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    private long amount;


}
