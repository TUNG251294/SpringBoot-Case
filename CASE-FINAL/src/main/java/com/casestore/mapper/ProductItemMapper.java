package com.casestore.mapper;

import com.casestore.model.dto.ProductItemDto;
import com.casestore.model.dto.sf.request.SFOrderDetailtRequestDto;
import com.casestore.model.entity.*;
import com.casestore.repository.IProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductItemMapper {
    @Autowired
    private IProductItemRepository productItemRepository;
    public List<ProductItem> toEntities(List<ProductItemDto> productItemDtoList, Purchase purchase){
        List<ProductItem> result = new ArrayList<>();
        for(ProductItemDto ele: productItemDtoList){
            ProductItem productItem = toEntity(ele, purchase);
            result.add(productItem);
        }
        return result;
    }
    private ProductItem toEntity(ProductItemDto ele, Purchase purchase){
        ProductItem result = new ProductItem();
        result.setPurchase(purchase);
        result.setQuantity(ele.getQuantity());
        result.setProductName(ele.getProductName());
        return result;
    }

}
