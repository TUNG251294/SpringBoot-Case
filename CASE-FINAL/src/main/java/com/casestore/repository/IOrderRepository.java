package com.casestore.repository;


import com.casestore.model.entity.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends PagingAndSortingRepository<Order,Long> {
//    @Modifying
//    @Query("UPDATE Order o SET o.totalAmount = (" +
//            "SELECT SUM(od.quantity * od.price) FROM OrderDetail od WHERE od.order.id = :orderId" +
//            ") WHERE o.id = :orderId")
//    void updateTotalAmount(@Param("orderId") Long orderId);
//



//@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails")
//Page<Order> findAllWithOrderDetails(Pageable pageable);
}
