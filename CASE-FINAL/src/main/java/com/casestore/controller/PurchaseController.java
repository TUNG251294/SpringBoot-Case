package com.casestore.controller;

import com.casestore.model.ConfirmModel;
import com.casestore.model.PurchaseModelRequest;
import com.casestore.model.PurchaseModelResponse;
import com.casestore.model.entity.Purchase;
import com.casestore.repository.IPurchaseRepository;
import com.casestore.service.impl.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    IPurchaseRepository purchaseRepository;

//    B1: Dat hang va nhan 1 thong bao xac nhan kieu String
    @PostMapping("/purchase-inventory")
    public ResponseEntity<?> save(@RequestBody PurchaseModelRequest purchaseModelRequest) {
        ResponseEntity<?> response = purchaseService.restTemplateInventory(purchaseModelRequest);
        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);    //lay thong bao tu Linh gui, dang o dang tring trong RestTemplate
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    B2: Linh se gui hoa don gom OrderId, TotalPrice va Status, se phai set nhung gia tri này update bang Purchase
    @PostMapping("/received-model")
    public ResponseEntity<?> received(@RequestBody PurchaseModelResponse purchaseModelResponse){
       Double responseTotalPrice = purchaseModelResponse.getTotalPrice();
       String responseStatus = purchaseModelResponse.getStatus();
       Long orderId = purchaseModelResponse.getOrderId();
//       id = orderId gui di va phai bang orderId nhan, so sanh id va orderId nhan de them cac truong thieu vao DB
       Purchase purchase = purchaseRepository.findById(orderId).orElse(null);
       if(purchase != null){
           purchase.setStatus(responseStatus);
           purchase.setTotalPrice(responseTotalPrice);
           purchaseRepository.save(purchase);

           return new ResponseEntity<>(purchaseModelResponse, HttpStatus.OK);
       } else
        return new ResponseEntity<>(purchaseModelResponse, HttpStatus.BAD_REQUEST);
    }

//    B3: Sau khi da nhan hang se gui 1 doi tuong tra ve, Linh se nhan doi tuong nay de xu ly nghiep vu
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmModel confirmModel) {
        ResponseEntity<?> response = purchaseService.restTemplateConfirm(confirmModel);
        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.CREATED);    //lay thong bao tu Linh gui, dang o dang String trong RestTemplate
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

    @GetMapping("/purchase/{id}")
    public ResponseEntity<Purchase> list(@PathVariable Long id) {
        Optional<Purchase> purchase = purchaseService.findById(id);
        return new ResponseEntity<>(purchase.orElse(null), HttpStatus.OK);
    }

    @GetMapping("/purchase/list")
    public ResponseEntity<Page<Purchase>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<Purchase> purchases = purchaseService.findAll(pageable);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

}
