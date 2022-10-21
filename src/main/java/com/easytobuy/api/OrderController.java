package com.easytobuy.api;

import com.easytobuy.model.OrderRequest;
import com.easytobuy.model.OrderResponse;
import com.easytobuy.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/orders")
@CrossOrigin
@AllArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest){
       return new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<OrderResponse> getOrderDetailsByOrderId(@PathVariable String orderId){
        return new ResponseEntity<>(orderService.getOrderDteailsByOrderId(orderId), HttpStatus.OK);
    }

}
