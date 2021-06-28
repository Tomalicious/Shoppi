package com.example.shopr.repositories;


import com.example.shopr.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addOrder(Orders newOrders) {
        entityManager.persist(newOrders);
    }

    public org.hibernate.criterion.Order findById(Long id) {
        TypedQuery query = entityManager.createQuery("select o from Orders o where o.id = :id", Orders.class);
        query.setParameter("id", id);
        return (org.hibernate.criterion.Order) query.getSingleResult();

    }

    @Transactional
    public void remove(Orders orders) {
        entityManager.remove(orders);
    }

    @Transactional
    public void addBookNonToOrder(BookNonFiction book, Long id) {
        entityManager.merge(book);
    }


    @Transactional
    public void addBookFictionToOrder(BookFiction bookFiction, Long id) {
        entityManager.merge(bookFiction);
    }

    @Transactional
    public void addLpListToOrder(Lp lpList, Long id) {
        entityManager.merge(lpList);
    }


    @Transactional
    public void addGameToOrder(Game game, Long id) {
        entityManager.merge(game);
    }


    @Transactional
    public void newOrder(Orders orders) {
        entityManager.persist(orders);

    }


    public Orders getOrder(Long orderId) {
        Query query = entityManager.createQuery("select o from Orders o where o.id = :id");
        query.setParameter("id" , orderId);
        return (Orders) query.getSingleResult();


    }

    @Transactional
    public void updateOrder(Orders orders) {
        entityManager.merge(orders);
    }

    @Transactional
    public void removeById(Long orderId) {
        Query query = entityManager.createQuery("delete from Orders o where o.id = :id");
        query.setParameter("id" , orderId);
        query.executeUpdate();
    }
}
