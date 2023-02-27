package com.casestore.repository;

import com.casestore.model.entity.Purchase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepository extends PagingAndSortingRepository<Purchase,Long> {
//    PurchaseModelRequest orderInventory(PurchaseModelRequest purchaseModel);
}
