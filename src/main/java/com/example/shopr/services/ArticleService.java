package com.example.shopr.services;


import com.example.shopr.domain.Article;
import com.example.shopr.domain.Book;
import com.example.shopr.domain.Game;
import com.example.shopr.domain.Lp;
import com.example.shopr.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
