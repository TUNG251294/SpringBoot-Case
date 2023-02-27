package com.casestore.model.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PurchaseDto {
    private Long id;
    private String status;
    private Double totalPrice;
}
