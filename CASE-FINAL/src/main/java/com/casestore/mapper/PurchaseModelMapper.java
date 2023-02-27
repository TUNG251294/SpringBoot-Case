package com.casestore.mapper;

import com.casestore.model.dto.PurchaseDto;
import com.casestore.model.entity.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseModelMapper {
    public PurchaseDto toResponseDto(Purchase purchase) {
        PurchaseDto response = new PurchaseDto();
        response.setId(purchase.getId());
        response.setStatus(purchase.getStatus());
        response.setTotalPrice(purchase.getTotalPrice());
        return response;
    }
}
