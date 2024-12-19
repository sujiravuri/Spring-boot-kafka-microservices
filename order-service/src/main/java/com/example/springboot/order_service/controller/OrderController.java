package com.example.springboot.order_service.controller;

import com.example.springboot.base_domains.DTO.Order;
import com.example.springboot.base_domains.DTO.OrderEvent;
import com.example.springboot.order_service.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("api/orderpublish")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order)
    {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order Status is in pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);

     return "Order placed successfully";
    }

}
