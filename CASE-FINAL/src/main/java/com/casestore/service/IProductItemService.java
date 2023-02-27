package com.casestore.service;

import com.casestore.model.PurchaseModelRequest;
import com.casestore.model.entity.ProductItem;

public interface IProductItemService extends IGeneralService<ProductItem> {
    PurchaseModelRequest send(PurchaseModelRequest purchaseModelRequest);

}
