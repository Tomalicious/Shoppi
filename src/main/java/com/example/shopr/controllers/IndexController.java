package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.domainenums.Auth;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
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
    public String showIndex(Model model, HttpSession session) {
        model.addAttribute("users" , userService.getUsers());
        model.addAttribute("newUser" , new User());
        model.addAttribute("registerNewUser" , new User());
        session.setAttribute("name", "value");
        return "index";
    }

    @GetMapping(value = "/employeeArticlePage")
    public String showArticlesEmployee(Model model) {
        model.addAttribute("allArticles" ,articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/chosenUser")
    public String splitProgram(Model model , @ModelAttribute User newUser){
        if(userService.findPass(newUser.getWebUser()).equals(newUser.getPassword()) && userService.findAuth(newUser.getWebUser()).equals(Auth.EMPLOYEE)){
            model.addAttribute("allArticles" , articleService.getAll());

            return "allArticlesEmp";
        }else if(userService.findPass(newUser.getWebUser()).equals(newUser.getPassword()) && userService.findAuth(newUser.getWebUser()).equals(Auth.CLIENT)){
            model.addAttribute("allArticles" ,articleService.getAll());
            model.addAttribute("userId" , userService.findId(newUser.getWebUser()));
            return "allArticlesClient";
        }else {
            return "error2";
        }
    }

    @GetMapping(value = "/allArticlesClient/{userId}")
    public String showAllArticles(Model model , @PathVariable("userId")Long userId) {
        model.addAttribute("allArticles" ,articleService.getAll());
        model.addAttribute("userId" , userId);
        return "allArticlesClient";
    }

    @PostMapping(value = "/register")
    public String register(Model model , @ModelAttribute User registerNewUser){
        registerNewUser.setAuth(Auth.valueOf("CLIENT"));
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

    @PostMapping(value = "/addedBook")
    public String add(@ModelAttribute BookFiction newBook , Model model) {
        articleService.addBook(newBook);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/addedNonFictiveBook")
    public String add(@ModelAttribute BookNonFiction newBook , Model model) {
        articleService.addBook(newBook);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/addedGame")
    public String add(@ModelAttribute Game newGame , Model model) {
        articleService.addGame(newGame);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/addedLp")
    public String add(@ModelAttribute Lp  newLp, Model model) {
        articleService.addLp(newLp);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @GetMapping(value = "/detailedSearchOnParam")
    public String showDetailedSearch (Model model , @ModelAttribute DetailParams detailSearch) {
        model.addAttribute("allArticles" , articleService.getAll());
        return "detailedSearch";
    }
}
