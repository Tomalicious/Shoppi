package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addBookNonFictionToOrder(BookNonFiction bookNonFiction, Long id) {
        orderRepository.addBookNonToOrder(bookNonFiction, id);
    }

    public void addBookFictionToOrder(BookFiction bookFiction, Long id) {
        orderRepository.addBookFictionToOrder(bookFiction, id);
    }

    public void addGameToOrder(Game game, Long id) {
        orderRepository.addGameToOrder(game , id);

    }

    public void addLpListToOrder(List<Lp> lpList, Long id) {
        orderRepository.addLpListToOrder(lpList , id);
    }

    public void newOrder(Orders orders) {
        orderRepository.newOrder(orders);
    }
}
