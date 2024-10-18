package com.tokioschool.myshop.service.impl;

import com.tokioschool.myshop.domain.Order;
import com.tokioschool.myshop.domain.OrderDetail;
import com.tokioschool.myshop.repository.OrderDetailRepository;
import com.tokioschool.myshop.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Set<OrderDetail> findByOrderNumber(String orderNumber) {
        return orderDetailRepository.findByOrderNumber(orderNumber);
    }
    
	@Override
	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		return orderDetailRepository.save(orderDetail);
	
	}
}
