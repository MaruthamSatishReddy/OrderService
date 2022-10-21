package com.easytobuy.service;

import com.easytobuy.model.OrderRequest;
import com.easytobuy.model.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);
    OrderResponse getOrderDteailsByOrderId(String orderId);
    List<OrderResponse> getAllOrders();
}
