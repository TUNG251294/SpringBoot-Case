package com.casestore.controller;

import com.casestore.model.entity.ProductItem;
import com.casestore.service.IProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductItemController {
    @Autowired
    private IProductItemService productItemService;

    @GetMapping("/product-item/list")
    public ResponseEntity<Page<ProductItem>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<ProductItem> productItems = productItemService.findAll(pageable);
        return new ResponseEntity<>(productItems, HttpStatus.OK);
    }
}
