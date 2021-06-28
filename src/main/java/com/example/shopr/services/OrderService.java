package com.example.shopr.services;


import com.example.shopr.domain.*;
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

    public void remove(Orders orders) {
        orderRepository.remove(orders);
    }

    public void addBookNonFictionToOrder(BookNonFiction bookNonFiction, Long id) {
        orderRepository.addBookNonToOrder(bookNonFiction, id);
    }

    public void addBookFictionToOrder(BookFiction bookFiction, Long id) {
        orderRepository.addBookFictionToOrder(bookFiction, id);
    }

    public void addGameToOrder(Game game, Long id) {
        orderRepository.addGameToOrder(game , id);

    }

    public void addLpListToOrder(Lp lp, Long id) {
        orderRepository.addLpListToOrder(lp, id);
    }

    public void newOrder(Orders orders) {
        orderRepository.newOrder(orders);
    }

    public Orders getOrder(Long orderId) {
        return orderRepository.getOrder(orderId);
    }

    public void updateOrder(Orders orders) {
        orderRepository.updateOrder(orders);
    }

    public void removeById(Long orderId) {
        orderRepository.removeById(orderId);
    }
}