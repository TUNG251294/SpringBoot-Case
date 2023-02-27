package com.casestore.controller;

import com.casestore.model.dto.bo.response.OrderDtoResponse;
import com.casestore.model.dto.sf.request.SFOrderDtoRequest;
import com.casestore.model.dto.sf.response.SFOrderDtoResponse;
import com.casestore.service.SFOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/store-front")
public class SFOrderController {

    @Autowired
    private SFOrderService sfOrderService;

    @PostMapping("/order")
    public ResponseEntity<SFOrderDtoResponse> save(@RequestBody SFOrderDtoRequest requestDto) {
        return new ResponseEntity<>(sfOrderService.save(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/order/list")
    public ResponseEntity<Page<OrderDtoResponse>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<OrderDtoResponse> orderDtoResponses = sfOrderService.findAll(pageable);
        return new ResponseEntity<>(orderDtoResponses, HttpStatus.OK);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getDtoById(@PathVariable Long id) {
        Optional<OrderDtoResponse> orderDtoResponse = sfOrderService.findById(id);
        if(orderDtoResponse != null){
            return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found order with this id", HttpStatus.NOT_FOUND);
        }
    }
//    Hibernate không tự động nạp tất cả OrderDetails cho các Order để tránh tình trạng lazy loading, trả về []

}
