package com.casestore.repository;

import com.casestore.model.entity.ProductItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductItemRepository extends PagingAndSortingRepository<ProductItem,Long> {
}
