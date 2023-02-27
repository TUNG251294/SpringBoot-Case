package com.casestore.model.dto.sf.request;

import lombok.Data;

@Data
public class SFOrderDetailtRequestDto {
    private Long productId;

    private Integer quantity;
}
