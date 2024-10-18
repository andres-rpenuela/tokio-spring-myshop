package com.tokioschool.myshop.service.impl;

import com.tokioschool.myshop.domain.Order;
import com.tokioschool.myshop.repository.OrderRepository;
import com.tokioschool.myshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findByNumber(String number) {
        return orderRepository.findByNumber(number);
    }

    @Override
    public Set<Order> findByUsername(String username) {
        return orderRepository.findByUserUsername(username);
    }

	@Override
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	
	}
}
