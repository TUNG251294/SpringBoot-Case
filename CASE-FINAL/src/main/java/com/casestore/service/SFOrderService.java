package com.casestore.service;

import com.casestore.model.dto.bo.response.OrderDtoResponse;
import com.casestore.model.dto.sf.request.SFOrderDtoRequest;
import com.casestore.model.dto.sf.response.SFOrderDtoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SFOrderService {
    SFOrderDtoResponse save(SFOrderDtoRequest requestDto);

    Page<OrderDtoResponse> findAll(Pageable pageable);

    Optional<OrderDtoResponse> findById(Long id);
}
