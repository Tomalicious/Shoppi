package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.domainenums.Authorization;
import com.example.shopr.domainenums.BookGenre;
import com.example.shopr.domainenums.GameGenre;
import com.example.shopr.domainenums.LpGenre;
import com.example.shopr.services.ArticleService;
import com.example.shopr.services.ContactService;
import com.example.shopr.services.DetailService;
import com.example.shopr.services.UserService;
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
    private DetailService detailService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;


    @GetMapping(value = "/")
    public String showIndex(Model model) {
        model.addAttribute("users" , userService.getUsers());
        model.addAttribute("newUser" , new User());
        model.addAttribute("registerNewUser" , new User());
        return "index";
    }

    @PostMapping(value = "/chosenUser")
    public String splitProgram(Model model , @ModelAttribute User newUser){
        if(userService.findPass(newUser.getUser()).equals(newUser.getPassword()) && userService.findAuth(newUser.getUser()).equals(Authorization.EMPLOYEE)){
            model.addAttribute("allArticles" , articleService.getAll());

            return "allArticlesEmp";
        }else if(userService.findPass(newUser.getUser()).equals(newUser.getPassword()) && userService.findAuth(newUser.getUser()).equals(Authorization.CLIENT)){
            model.addAttribute("allArticles" ,articleService.getAll());
            model.addAttribute("userId" , userService.findId(newUser.getUser()));
            return "allArticlesClient";
        }else {
            return "error2";
        }
    }

    @PostMapping(value = "/register")
    public String register(Model model , @ModelAttribute User registerNewUser){
        registerNewUser.setAuthorization(Authorization.valueOf("CLIENT"));
        contactService.saveContact(registerNewUser);
        return "redirect:/";
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
            model.addAttribute("genreList" , LpGenre.values());
            model.addAttribute("newLp" , new Lp());
            return "addingLp";
        } else {
            model.addAttribute("genreList" , GameGenre.values());
            model.addAttribute("newGame" , new Game());
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

    @PostMapping(value = "/addedBook")
    public String add(@ModelAttribute BookFiction newBook) {
        articleService.addBook(newBook);
        return "redirect:/";
    }

    @PostMapping(value = "/addedNonFictiveBook")
    public String add(@ModelAttribute BookNonFiction newBook) {
        articleService.addBook(newBook);
        return "redirect:/";
    }

    @PostMapping(value = "/addedGame")
    public String add(@ModelAttribute Game newGame) {
        articleService.addGame(newGame);
        return "redirect:/";
    }

    @PostMapping(value = "/addedLp")
    public String add(@ModelAttribute Lp  newLp) {
        articleService.addLp(newLp);
        return "redirect:/";
    }

    @GetMapping(value = "/detailedSearchOnParam")
    public String showDetailedSearch (Model model , @ModelAttribute DetailParams detailSearch) {
        model.addAttribute("allArticles" , articleService.getAll());
        return "detailedSearch";
    }
}
