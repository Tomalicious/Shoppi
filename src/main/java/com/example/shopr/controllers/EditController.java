package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.domainenums.BookGenre;
import com.example.shopr.domainenums.GameGenre;
import com.example.shopr.domainenums.LpGenre;
import com.example.shopr.domainenums.Subject;
import com.example.shopr.services.ArticleService;
import com.example.shopr.services.DetailService;
import com.example.shopr.services.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditController {

    @Autowired
    private DetailService detailService;

    @Autowired
    private EditService editService;

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/editArticle/{id}/{type}")
    public String editBook(Model model, @PathVariable("id") Long id, @PathVariable("type") String type) {
        if (detailService.findById(id, type).getClass() == BookFiction.class) {
            model.addAttribute("newBook" , new BookFiction());
            BookFiction book = (BookFiction) detailService.findById(id , type);
            model.addAttribute("books" , book);
            model.addAttribute("genreList" , BookGenre.values());

            return "editingFictiveBook";
        } else if(detailService.findById(id, type).getClass() == BookNonFiction.class) {
            model.addAttribute("newBook" , new BookNonFiction());
            BookNonFiction book = (BookNonFiction) detailService.findById(id , type);
            model.addAttribute("books" , book);
            model.addAttribute("subjectList", Subject.values());

            return "editingNonFictiveBook";
        }else if(detailService.findById(id, type).getClass() == Game.class){
            model.addAttribute("newGame" , new Game());
            Game game = (Game) detailService.findById(id , type);
            model.addAttribute("games" , game);
            model.addAttribute("gameGenres" , GameGenre.values());
            return"editingGame";
        }else{
            model.addAttribute("newLp" , new Lp());
            Lp lp = (Lp) detailService.findById(id , type);
            model.addAttribute("lps" , lp);
            model.addAttribute("genres" , LpGenre.values());
            return "editingLp";
        }
    }

    @PostMapping(value = "/editBookFiction/{id}/{type}")
    public String editBook(@ModelAttribute BookFiction bookFiction , Model model) {
        editService.updateBook(bookFiction);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/editBookNon/{id}/{type}")
    public String editBookNon(@ModelAttribute BookNonFiction bookNonFiction , Model model) {
        editService.updateBookNon(bookNonFiction);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/editingGame/{id}/{type}")
    public String editGame(@ModelAttribute Game game , Model model) {
        editService.updateGame(game);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }

    @PostMapping(value = "/editingLp/{id}/{type}")
    public String editLp(@ModelAttribute Lp lp , Model model) {
        editService.updateLp(lp);
        model.addAttribute("allArticles" , articleService.getAll());
        return "allArticlesEmp";
    }


}
