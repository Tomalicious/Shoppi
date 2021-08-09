package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.ArticleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepositoryImpl articleRepositoryImpl;

    public List<Article> getAll() {
        return articleRepositoryImpl.findAll();
    }

    public void addBook(Book newBook) {
        articleRepositoryImpl.addBook(newBook);
    }

    public void addGame(Game newGame) {
        articleRepositoryImpl.addGame(newGame);
    }

    public void addLp(Lp newLp) {
        articleRepositoryImpl.addLp(newLp);
    }

    public List<Article> articleListConverter(Orders order) {
        List<Article> articles = new ArrayList<>();
        articles.addAll(order.getBookFictionList());
        articles.addAll(order.getBookNonList());
        articles.addAll(order.getLpList());
        articles.addAll(order.getGamesList());
        return articles;
    }
}
