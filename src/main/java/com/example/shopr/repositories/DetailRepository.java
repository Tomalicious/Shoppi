package com.example.shopr.repositories;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class DetailRepository {



    @PersistenceContext
    private EntityManager entityManager;

}
