package com.example.shopr.controllers;


import com.example.shopr.domain.*;
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

    @GetMapping(value = "detailedArticle/{id}")
    public String showDetailsPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("lp", detailService.findById(id));
        if (detailService.findById(id).getClass() == BookFiction.class || detailService.findById(id).getClass() == BookNonFiction.class) {
            Book book = (Book) detailService.findById(id);
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
        else if (detailService.findById(id).getClass() == Game.class) {
            Game game = (Game) detailService.findById(id);
            model.addAttribute("game" , game);
            return "detailsGame";
        }else {
            Lp lp = (Lp) detailService.findById(id);
            model.addAttribute("lp" , lp);
            return "detailsLp";
        }
    }


}
