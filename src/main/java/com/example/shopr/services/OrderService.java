package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.ArticleRepository;
import com.example.shopr.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;


    public void remove(Orders orders){
        orderRepository.remove(orders);
    }


    public Orders getOrder(Long orderId) {
        return orderRepository.getOrder(orderId);
    }

    public void alterStock(Orders orders) {
        List<Article> articles = articleService.articleListConverter(orders);
        for(Article a : articles){
           Article art = articleRepository.findAll().stream().filter(article -> (article.getId() == a.getId() && article.getType().equals(a.getType()))).findFirst().get();
           art.setStock((art.getStock() - a.getOrderQuantity().getQuantity()));
        }

    }

    public void newOrder(Orders orders) {
        orderRepository.newOrder(orders);
    }
}
