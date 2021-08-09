package com.example.shopr.repositories;


import com.example.shopr.domain.*;
import com.example.shopr.interfaces.ArticleLookup;
import com.example.shopr.interfaces.ArticleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository, ArticleLookup {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        List<Game> gamesList = entityManager.createQuery("select g from Game g order by g.type , g.title , g.publisher, g.price ", Game.class).getResultList();
        List<Lp> lpList = entityManager.createQuery("select l from Lp l order by l.type , l.title, l.publisher , l.price", Lp.class).getResultList();
        List<Book> bookList = entityManager.createQuery("select b from Book b order by b.type , b.title , b.author , b.publisher , b.price", Book.class).getResultList();
        articleList.addAll(bookList);
        articleList.addAll(gamesList);
        articleList.addAll(lpList);
        return articleList;
    }

    @Override
    public Article findById(Long aLong) {
        return null;
    }

    @Override
    public Article save(Article entity) {
        return null;
    }

    @Override
    public void remove(Long aLong) {

    }

    @Override
    public List<Article> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Article> findByPriceGreaterThan(Double price) {
        return null;
    }

    @Transactional
    public void addBook(Book newBook) {
        entityManager.persist(newBook);
    }

    @Transactional
    public void addGame(Game newGame) {
        entityManager.persist(newGame);
    }

    @Transactional
    public void addLp(Lp newLp) {
        entityManager.persist(newLp);
    }
    @Transactional
    public void removeArticle(Article article) {
        entityManager.remove(article);
    }



}
