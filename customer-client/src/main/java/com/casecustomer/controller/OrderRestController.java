package com.casecustomer.controller;

import com.casecustomer.model.Order;
import com.casecustomer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderRestController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/send")
    public ResponseEntity<ResponseStatus> send(@RequestBody Order order) {
        orderService.sendOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
