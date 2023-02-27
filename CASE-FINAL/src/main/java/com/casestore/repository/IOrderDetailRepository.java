package com.casestore.repository;

import com.casestore.model.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
