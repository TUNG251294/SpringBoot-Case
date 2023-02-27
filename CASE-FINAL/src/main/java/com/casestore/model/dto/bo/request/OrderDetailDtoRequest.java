package com.casestore.model.dto.bo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailDtoRequest implements Serializable {

    private Long id;
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
