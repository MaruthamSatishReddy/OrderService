package com.easytobuy.service;

import com.easytobuy.entity.Order;
import com.easytobuy.exception.CustomException;
import com.easytobuy.external.clients.PaymentService;
import com.easytobuy.external.clients.ProductService;
import com.easytobuy.external.model.PaymentRequest;
import com.easytobuy.model.OrderRequest;
import com.easytobuy.model.OrderResponse;
import com.easytobuy.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Order order=Order.builder()
                .orderId(UUID.randomUUID().toString().split("-")[0])
                .productId(orderRequest.getProductId())
                .orderStatus("CREATED")
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .amount(orderRequest.getAmount())
                .build();
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        //Call Payment API
        PaymentRequest paymentRequest=PaymentRequest.builder()
                .orderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode().toString())
                .amount(orderRequest.getAmount())
                .build();
        String orderStatus;
                try{
                  paymentService.doPayment(paymentRequest);
                    orderStatus="PLACED";
                }
        catch (Exception exception){
            orderStatus="PAYMENT_FAILED";
        }
                order.setOrderStatus(orderStatus);
                orderRepository.save(order);
    return OrderResponse.builder()
            .orderStatus(order.getOrderStatus())
            .orderId(order.getOrderId())
            .amount(order.getAmount())
            .orderDate(order.getOrderDate())
            .build();
    }

  @Override
  public OrderResponse getOrderDteailsByOrderId(String orderId) {
    Order order = orderRepository.findById(orderId)
      .orElseThrow(()->new CustomException("Order not found by given OrderId","NOT_FOUND", 404));
    return OrderResponse.builder()
            .orderDate(order.getOrderDate())
            .orderId(order.getOrderId())
            .orderStatus(order.getOrderStatus())
            .build();
  }

    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }
}
