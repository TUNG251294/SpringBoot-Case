package com.casestore.mapper;

import com.casestore.model.dto.sf.request.SFOrderDetailtRequestDto;
import com.casestore.model.entity.Order;
import com.casestore.model.entity.OrderDetail;
import com.casestore.model.entity.Product;
import com.casestore.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailMapper {

    @Autowired
    private IProductRepository productRepository;

    public List<OrderDetail> toEntities(List<SFOrderDetailtRequestDto> orderDetailtRequestDtoList, Order order) {
        List<OrderDetail> result = new ArrayList<>();
        for(SFOrderDetailtRequestDto ele : orderDetailtRequestDtoList){
            OrderDetail orderDetail = toEntity(ele, order);
            result.add(orderDetail);
        }
        return result;
    }

    private OrderDetail toEntity(SFOrderDetailtRequestDto ele, Order order) {
        OrderDetail result = new OrderDetail();
        result.setOrder(order);
        Product product = productRepository.findById(ele.getProductId()).orElse(null);
        result.setProduct(product);
        result.setQuantity(ele.getQuantity());
        return result;
    }
}
