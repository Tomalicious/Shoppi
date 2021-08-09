package com.example.shopr.interfaces;


import com.example.shopr.domain.Article;
import java.util.List;

public interface ArticleLookup {

    public List<Article> findByTitle(String title);

    public List<Article> findByPriceGreaterThan(Double price);
}
