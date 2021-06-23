package com.example.shopr.services;


import com.example.shopr.domain.Article;
import com.example.shopr.domain.Game;
import com.example.shopr.repositories.ArticleRepository;
import com.example.shopr.repositories.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article findById(Long id) {
       return articleRepository.findAll().stream().filter(article -> article.getId() == id).findFirst().get();

    }
}
