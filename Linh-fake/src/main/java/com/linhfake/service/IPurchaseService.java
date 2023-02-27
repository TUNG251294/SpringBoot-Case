package com.linhfake.service;

import com.linhfake.model.PurchaseModel;

public interface IPurchaseService extends IGeneralService<PurchaseModel>{
    PurchaseModel save(PurchaseModel purchaseModel);
}
