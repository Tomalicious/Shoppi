package com.example.shopr.services;


import com.example.shopr.domain.Orders;
import com.example.shopr.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Orders orders) {
        orderRepository.addOrder(orders);
    }

    public void remove(Long orderId) {
        orderRepository.remove(orderId);
    }
}
