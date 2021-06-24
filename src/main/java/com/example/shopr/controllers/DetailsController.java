package com.example.shopr.controllers;


import com.example.shopr.domain.*;
import com.example.shopr.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.lang.Integer.parseInt;

@Controller
public class DetailsController {

    @Autowired
    private DetailService detailService;

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

    @GetMapping(value = "/removeArticle/{id}/{type}")
    public String deleteBook(@PathVariable("id") Long id , @PathVariable("type") String type) {
        detailService.removeArticleById(id , type);
        return "redirect:/";
    }
}
