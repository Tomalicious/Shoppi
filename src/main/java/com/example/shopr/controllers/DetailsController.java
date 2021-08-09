package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.services.ArticleService;
import com.example.shopr.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailsController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "detailedArticle/{id}/{type}")
    public String showDetailsPage(Model model, @PathVariable("id") Long id , @PathVariable("type") String type) {
        model.addAttribute("lp", detailService.findById(id , type));
        if (detailService.findById(id , type).getClass() == BookFiction.class || detailService.findById(id , type).getClass() == BookNonFiction.class) {
            Book book = (Book) detailService.findById(id , type);
            if(book.getType().equals("FICTION")){
                BookFiction bookfiction = (BookFiction) book;
                model.addAttribute("book" , bookfiction);
                return "detailsBookFiction";
            }else {
                BookNonFiction bookNonFiction = (BookNonFiction) book;
                model.addAttribute("book" , bookNonFiction);
                model.addAttribute("subject" , bookNonFiction.getSubject());
                return "detailsBook";
            }
        }
        else if (detailService.findById(id , type).getClass() == Game.class) {
            Game game = (Game) detailService.findById(id , type);
            model.addAttribute("game" , game);
            return "detailsGame";
        }else {
            Lp lp = (Lp) detailService.findById(id , type);
            model.addAttribute("lp" , lp);
            return "detailsLp";
        }
    }

    @GetMapping(value = "/detailedArticleClient/{id}/{type}/{userId}")
    public String showDetailsPageClient(Model model, @PathVariable("id") Long id , @PathVariable("type") String type, @PathVariable("userId") Long userId) {
        model.addAttribute("lp", detailService.findById(id , type));
        if (detailService.findById(id , type).getClass() == BookFiction.class || detailService.findById(id , type).getClass() == BookNonFiction.class) {
            Book book = (Book) detailService.findById(id , type);
            if(book.getType().equals("FICTION")){
                BookFiction bookfiction = (BookFiction) book;
                model.addAttribute("book" , bookfiction);
                model.addAttribute("userId" , userId);
                return "clientDetailsBookFiction";
            }else {
                BookNonFiction bookNonFiction = (BookNonFiction) book;
                model.addAttribute("book" , bookNonFiction);
                model.addAttribute("subject" , bookNonFiction.getSubject());
                model.addAttribute("userId" , userId);
                return "clientDetailsBook";
            }
        }
        else if (detailService.findById(id , type).getClass() == Game.class) {
            Game game = (Game) detailService.findById(id , type);
            model.addAttribute("game" , game);
            model.addAttribute("userId" , userId);
            return "clientDetailsGame";
        }else {
            Lp lp = (Lp) detailService.findById(id , type);
            model.addAttribute("lp" , lp);
            model.addAttribute("userId" , userId);
            return "clientDetailsLp";
        }
    }

    @GetMapping(value = "/removeArticle/{id}/{type}")
    public String deleteBook(@PathVariable("id") Long id , @PathVariable("type") String type, Model model) {
        detailService.removeArticleById(id , type);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }
}
