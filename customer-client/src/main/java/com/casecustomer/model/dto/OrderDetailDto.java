package com.casecustomer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailDto implements Serializable {
    private Long id;
    private Integer quantity;
    private OrderDto orderDto;  //k duoc truyen entity lam property cua 1 DTO
    private ProductDto productDto;
}
