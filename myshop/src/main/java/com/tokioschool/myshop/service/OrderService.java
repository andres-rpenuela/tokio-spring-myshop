package com.tokioschool.myshop.service;

import com.tokioschool.myshop.domain.Order;

import java.util.Set;

/**
 * Service para gestión de pedidos
 */
public interface OrderService {

    Order findByNumber(String number);
    Set<Order> findByUsername(String username);
    Order addOrder(Order order);

}
