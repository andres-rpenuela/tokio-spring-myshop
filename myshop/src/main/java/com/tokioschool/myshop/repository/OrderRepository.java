package com.tokioschool.myshop.repository;

import com.tokioschool.myshop.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findByNumber(String number);
    Set<Order> findByUserUsername(@Param("user.username") String username);
}
