package com.example.shopr.controllers;


import com.example.shopr.domain.*;
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

    @GetMapping(value = "/editArticle/{id}/{type}")
    public String editBook(Model model, @PathVariable("id") Long id, @PathVariable("type") String type) {
        if (detailService.findById(id, type).getClass() == BookFiction.class) {
            model.addAttribute("newBook" , new BookFiction());
            BookFiction book = (BookFiction) detailService.findById(id , type);
            model.addAttribute("books" , book);
            model.addAttribute("genreList" , BookGenre.values());
            model.addAttribute("subjectList", Subject.values());

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
    public String editBook(@ModelAttribute BookFiction bookFiction) {
        editService.updateBook(bookFiction);
        return "redirect:/";
    }

    @PostMapping(value = "/editBookNon/{id}/{type}")
    public String editBookNon(@ModelAttribute BookNonFiction bookNonFiction) {
        editService.updateBookNon(bookNonFiction);
        return "redirect:/";
    }

    @PostMapping(value = "/editingGame/{id}/{type}")
    public String editGame(@ModelAttribute Game game) {
        editService.updateGame(game);
        return "redirect:/";
    }

    @PostMapping(value = "/editingLp/{id}/{type}")
    public String editLp(@ModelAttribute Lp lp) {
        editService.updateLp(lp);
        return "redirect:/";
    }


}
