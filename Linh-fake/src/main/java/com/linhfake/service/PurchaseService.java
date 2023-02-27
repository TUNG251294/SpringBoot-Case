package com.linhfake.service;

import com.linhfake.model.ProductItemDto;
import com.linhfake.model.PurchaseModel;
import com.linhfake.reposirory.IProductItemRepository;
import com.linhfake.reposirory.IPurchaseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService{
    @Autowired
    private IPurchaseRepository purchaseRepository;
    @Autowired
    private IProductItemRepository productItemRepository;
    //dung la phai luu bang dto, hien tai luu bang object, gia lap 1 dto de save duoc List<ProductItem> trong object
    @Override
    public PurchaseModel save(PurchaseModel purchaseModel) {
        PurchaseModel purchaseModelDto = new PurchaseModel();
        BeanUtils.copyProperties(purchaseModel,purchaseModelDto);

        List<ProductItemDto> productItemDtos = new ArrayList<>();
        List<ProductItemDto> productItemDtosOfRequest = purchaseModel.getProductItemListDto();
        for(ProductItemDto p: productItemDtosOfRequest){
            ProductItemDto productItemDto = new ProductItemDto();
            BeanUtils.copyProperties(p,productItemDto);

            productItemRepository.save(productItemDto);
        }
        purchaseModelDto.setProductItemListDto(productItemDtos);
        purchaseRepository.save(purchaseModelDto);
        return purchaseModel;
    }


}
