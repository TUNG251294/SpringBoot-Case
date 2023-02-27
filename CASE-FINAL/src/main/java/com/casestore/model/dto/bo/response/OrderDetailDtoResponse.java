package com.casestore.model.dto.bo.response;

import com.casestore.model.dto.OrderDto;
import com.casestore.model.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderDetailDtoResponse implements Serializable {

    private Long id;
    private Integer quantity;
    private OrderDto orderDto;
    private ProductDto productDto;
}
