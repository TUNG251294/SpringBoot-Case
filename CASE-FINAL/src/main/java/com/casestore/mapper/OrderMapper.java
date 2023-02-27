package com.casestore.mapper;

import com.casestore.model.dto.sf.response.SFOrderDtoResponse;
import com.casestore.model.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public SFOrderDtoResponse toResponseDto(Order order) {
        SFOrderDtoResponse response = new SFOrderDtoResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        return response;
    }
}
