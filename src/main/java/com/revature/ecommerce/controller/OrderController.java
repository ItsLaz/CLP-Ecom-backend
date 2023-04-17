package com.revature.ecommerce.controller;

import com.revature.ecommerce.model.CustomerOrder;
import com.revature.ecommerce.service.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CustomerOrder> placeOrder(@RequestParam Long userId){
        CustomerOrder customerOrder = orderService.placeOrder(userId);
        return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CustomerOrder>> getUserOrders(@PathVariable Long userId){
        List<CustomerOrder> customerOrders = orderService.getUserOrders(userId);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }
}
