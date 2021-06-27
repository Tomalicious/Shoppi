package com.example.shopr.repositories;


import com.example.shopr.domain.*;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addOrder(Orders newOrders) {
        entityManager.persist(newOrders);
    }

    public Order findById(Long id){
        TypedQuery query = entityManager.createQuery("select o from Orders o where o.id = :id", Orders.class);
        query.setParameter("id" , id);
        return (Order) query.getSingleResult();

    }

    @Transactional
    public void remove(Long orderId) {
        entityManager.remove(findById(orderId));
    }

    @Transactional
    public void addBookNonToOrder(BookNonFiction bookList, Long id) {
        entityManager.merge(bookList);
    }

    @Transactional
    public void addBookFictionToOrder(BookFiction bookFiction, Long id) {
        Query query = entityManager.createQuery("update ");
        query.setParameter("id" , bookFiction.getId());
        query.executeUpdate();
    }

    @Transactional
    public void addLpListToOrder(List<Lp> lpList, Long id) {
        entityManager.persist(lpList);
    }


    @Transactional
    public void addGameToOrder(Game game, Long id) {
        entityManager.persist(game);
    }


    @Transactional
    public void newOrder(Orders orders) {
        entityManager.persist(orders);
    }
}
