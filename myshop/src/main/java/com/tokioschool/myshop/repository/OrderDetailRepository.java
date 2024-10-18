package com.tokioschool.myshop.repository;

import com.tokioschool.myshop.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {

    Set<OrderDetail> findByOrderNumber(@Param("order.number") String number);
}
