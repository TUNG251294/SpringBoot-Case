package com.linhfake.controller;

import com.linhfake.model.PurchaseModel;
import com.linhfake.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private IPurchaseService purchaseService;
    @PostMapping("/order")
    public ResponseEntity<PurchaseModel> save(@RequestBody PurchaseModel purchaseModel) {
        return new ResponseEntity<>(purchaseService.save(purchaseModel), HttpStatus.CREATED);
    }
}
