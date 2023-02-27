package com.casestore.controller;


import com.casestore.model.dto.ProductDto;
import com.casestore.service.IProductService;
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
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/product/list")
    public ResponseEntity<Page<ProductDto>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<ProductDto> productDtos = productService.findAll(pageable);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> list(@PathVariable Long id) {
        Optional<ProductDto> productDto = productService.findById(id);
        if(productDto != null){
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found product with this id", HttpStatus.NOT_FOUND);
        }
    }
}
