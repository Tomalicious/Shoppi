package com.example.shopr.repositories;


import com.example.shopr.domain.Orders;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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

    public void remove(Long orderId) {
        entityManager.remove(findById(orderId));
    }
}
