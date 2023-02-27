package com.casecustomer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class ProductDto {
//    private Long id;
//    private int quantity;
//}
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
