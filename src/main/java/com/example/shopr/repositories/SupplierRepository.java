package com.example.shopr.repositories;


import com.example.shopr.domain.Supplier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SupplierRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long getMaxId() {
        List<Supplier> suppliers = entityManager.createQuery("select s from Supplier s", Supplier.class).getResultList();
        List<Long> longs = new ArrayList<>();
        Long counter = 0L;
        for(Supplier s : suppliers){
            longs.add(s.getId());
            counter++;
        }
        return counter;

    }

    public List<Supplier> findAll() {
        return entityManager.createQuery("select s from Supplier s" , Supplier.class).getResultList();
    }

    @Transactional
    public void updateOrderList(Supplier s) {
        entityManager.merge(s);
    }
}
