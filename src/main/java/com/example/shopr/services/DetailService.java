package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DetailService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article findById(Long id) {
       return articleRepository.findAll().stream().filter(article -> article.getId() == id).findFirst().get();

    }

    public void removeArticleById(Long id) {
        articleRepository.removeArticle(articleRepository.findAll().stream().filter(article -> article.getId() == id).findFirst().get());
    }
}
