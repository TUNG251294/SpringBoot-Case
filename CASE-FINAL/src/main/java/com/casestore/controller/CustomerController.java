package com.casestore.controller;

import com.casestore.model.dto.CustomerDto;
import com.casestore.model.dto.ProductDto;
import com.casestore.service.ICustomerService;
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
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/customer/list")
    public ResponseEntity<Page<CustomerDto>> list(@PageableDefault(value = 5) Pageable pageable) {
        Page<CustomerDto> customerDtos = customerService.findAll(pageable);
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<?> list(@PathVariable Long id) {
        Optional<CustomerDto> customerDto = customerService.findById(id);
        if(customerDto != null){
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found customer with this id", HttpStatus.NOT_FOUND);
        }
    }
}
