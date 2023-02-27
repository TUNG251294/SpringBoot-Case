package com.casestore.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailDto implements Serializable {

    private Long id;
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
