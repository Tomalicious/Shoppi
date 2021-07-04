package com.example.shopr.repositories;


import com.example.shopr.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public void remove(Orders orders) {

        entityManager.remove(orders);
    }


    @Transactional(rollbackOn = Exception.class)
    public void newOrder(Orders orders) {
        entityManager.persist(orders);

    }


    public Orders getOrder(Long orderId) {
        Query query = entityManager.createQuery("select o from Orders o where o.id = :id");
        query.setParameter("id" , orderId);
        return (Orders) query.getSingleResult();
    }

    @Transactional(rollbackOn = ExceededStockAmountException.class)
    public void updateOrder(Orders orders) throws ExceededStockAmountException {
        List<Article> articleList = new ArrayList<>();
        if(orders.getLpList() != null){
        articleList.addAll(orders.getLpList());
        }else if(orders.getGamesList() != null) {
            articleList.addAll(orders.getGamesList());
        }else if(orders.getBookNonList() != null) {
            articleList.addAll(orders.getBookNonList());
        }else if(orders.getBookFictionList() != null){
            articleList.addAll(orders.getBookFictionList());
        }
        for(Article a : articleList){
            if (a.getOrderQuantity().getQuantity() > a.getStock()){
                throw new ExceededStockAmountException("Order quantity should not exceed stock amount");
            }
        }
        entityManager.merge(orders);
    }

    @Transactional
    public void removeById(Long orderId) {
        Query query = entityManager.createQuery("delete from Orders o where o.id = :id");
        query.setParameter("id" , orderId);
        query.executeUpdate();
    }
}
