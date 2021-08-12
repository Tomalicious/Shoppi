package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.ArticleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DetailService {
    @Autowired
    private ArticleRepositoryImpl articleRepositoryImpl;

    public Article findById(Long id , String type) {
       return articleRepositoryImpl.findAll().stream().filter(article -> (article.getId() == id && article.getArticleType().equals(type))).findFirst().get();

    }

    public void removeArticleById(Long id , String type) {
        articleRepositoryImpl.removeArticle(articleRepositoryImpl.findAll().stream().filter(article -> (article.getId() == id && article.getArticleType().equals(type))).findFirst().get());
    }
}
