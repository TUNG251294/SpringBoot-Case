package com.casestore.model.dto.sf.request;

import lombok.Data;

import java.util.List;

@Data
public class SFOrderDtoRequest {

    private Long customerId;

    private List<SFOrderDetailtRequestDto> orderDetailtRequestDtoList;
}
