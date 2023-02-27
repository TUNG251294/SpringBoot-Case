package com.linhfake.reposirory;

import com.linhfake.model.ProductItemDto;
import com.linhfake.model.PurchaseModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductItemRepository extends CrudRepository<ProductItemDto,Long> {
}
