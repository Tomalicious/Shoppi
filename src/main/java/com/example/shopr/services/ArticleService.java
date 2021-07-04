package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public void addBook(Book newBook) {
        articleRepository.addBook(newBook);
    }

    public void addGame(Game newGame) {
        articleRepository.addGame(newGame);
    }

    public void addLp(Lp newLp) {
        articleRepository.addLp(newLp);
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
