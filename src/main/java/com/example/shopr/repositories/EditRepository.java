package com.example.shopr.repositories;


import com.example.shopr.domain.BookFiction;
import com.example.shopr.domain.BookNonFiction;
import com.example.shopr.domain.Game;
import com.example.shopr.domain.Lp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EditRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void updateBook(BookFiction bookFiction) {
        entityManager.merge(bookFiction);
    }

    public void updateBookNon(BookNonFiction bookNonFiction) {
        entityManager.merge(bookNonFiction);
    }

    public void updateGame(Game game) {
        entityManager.merge(game);
    }

    public void updateLp(Lp lp) {
        entityManager.merge(lp);
    }
}
