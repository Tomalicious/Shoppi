package com.example.shopr.repositories;


import com.example.shopr.domain.Article;
import com.example.shopr.domain.Book;
import com.example.shopr.domain.Game;
import com.example.shopr.domain.Lp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Article article){
        entityManager.persist(article);
    }
//
//    public Article findById(Long id , Article type){
//        TypedQuery query = entityManager.createQuery(" ");
//        return query.getSingleResult();
//    }
//
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        List<Game> gamesList = entityManager.createQuery("select g from Game g", Game.class).getResultList();
        List<Lp> lpList = entityManager.createQuery("select l from Lp l", Lp.class).getResultList();
        List<Book> bookList = entityManager.createQuery("select b from Book b", Book.class).getResultList();
        articleList.addAll(gamesList);
        articleList.addAll(bookList);
        articleList.addAll(lpList);
        return articleList;
    }

}
