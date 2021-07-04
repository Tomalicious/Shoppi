package com.example.shopr.repositories;


import com.example.shopr.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackOn = Exception.class)
    public void saveContact(User registerNewUser) {
        entityManager.persist(registerNewUser);
    }
}
