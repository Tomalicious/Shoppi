package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/")
    public String showIndex(Model model) {
        model.addAttribute("allArticles", articleService.getAll());
        return "index";
    }

    @GetMapping(value = "addingArticles")
    public String addArticlePage(Model model) {
        List<Type> articleTypes = new ArrayList<>();
        articleTypes.add(Type.builder().name("Book").number("1").build());
        articleTypes.add(Type.builder().name("Lp").number("2").build());
        articleTypes.add(Type.builder().name("Game").number("3").build());
        model.addAttribute("articleTypes", articleTypes);
        model.addAttribute("newArticle", new Type());
        return "addingArticle";
    }

    @PostMapping(value = "whichArticle")
    public String correctAddPage(Model model, @ModelAttribute Type newArticle) {
        if (newArticle.getNumber().equals("1")) {
            List<BookType> bookTypes = new ArrayList<>();
            bookTypes.add(BookType.builder().name("Fiction").number("1").build());
            bookTypes.add(BookType.builder().name("Non-Fiction").number("2").build());
            model.addAttribute("allBooks" , bookTypes);
            model.addAttribute("newBook" , new Type());
            return "addingWhichBooks";
        } else if (newArticle.getNumber().equals("2")) {
            return "addingLp";
        } else {
            return "addingGame";
        }
    }

    @PostMapping(value = "addingBook")
    public String addingBookPage(Model model ,@ModelAttribute Type newBook) {
        if(newBook.getNumber().equals("1")){
            model.addAttribute("newBook" , new BookFiction());
            model.addAttribute("genreList" , BookGenre.values());
            return "addingFictiveBook";
        }else{
            model.addAttribute("newBook" , new BookNonFiction());
            return "addingNonFictiveBook";
        }
    }

    @GetMapping(value = "addingLp")
    public String addingLpPage(Model model) {
        return "redirect:/";
    }
    @GetMapping(value = "addingGame")
    public String addingGamePage(Model model) {
        return "redirect:/";
    }

}




//    @GetMapping(value = "addingWhichBook")
//    public String addingWhichBookPage(Model model) {
//        List<BookType> bookTypes = new ArrayList<>();
//        bookTypes.add(BookType.builder().name("Fiction").number("1").build());
//        bookTypes.add(BookType.builder().name("Non-Fiction").number("2").build());
//        model.addAttribute("allBooks" , bookTypes);
//        model.addAttribute("newBook" , new Type());
//        return "addingWhichBooks";
//    }
