package com.tokioschool.myshop.service;

import com.tokioschool.myshop.domain.OrderDetail;

import java.util.Set;

/**
 * Service para gestión de detalles de pedido
 */
public interface OrderDetailService {

    Set<OrderDetail> findByOrderNumber(String orderNumber);
    OrderDetail addOrderDetail (OrderDetail orderDetail);
}
