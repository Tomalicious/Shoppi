package com.example.shopr.controllers;

import com.example.shopr.services.BasketService;
import com.example.shopr.services.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private DetailService detailService;

    @GetMapping(value = "/shopArticle/{id}/{type}")
    public String showBasketAmount (Model model , @PathVariable("id") Long id , @PathVariable("type") String type) {
        model.addAttribute("shopItem" , detailService.findById(id , type));
        //model.addAttribute("newQuantity" , )
        return "detailedSearch";
    }
}
