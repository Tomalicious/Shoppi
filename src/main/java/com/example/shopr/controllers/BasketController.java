package com.example.shopr.controllers;

import com.example.shopr.domain.*;
import com.example.shopr.services.ArticleService;
import com.example.shopr.services.BasketService;
import com.example.shopr.services.DetailService;
import com.example.shopr.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/shopArticle/{id}/{type}")
    public String showBasketAmount(Model model, @PathVariable("id") Long id, @PathVariable("type") String type) {
        model.addAttribute("article", detailService.findById(id, type));
        model.addAttribute("newQuantity", new Quantity());
        List<Quantity> quantityList = new ArrayList<>();
        for (int i = 1; i <= detailService.findById(id, type).getStock(); i++) {
            quantityList.add(Quantity.builder().quantity(i).build());
        }
        model.addAttribute("quantityList", quantityList);
        return "quantitySelection";
    }

    @GetMapping(value = "/shopArticleAnother/{id}/{type}/{orderId}")
    public String showBasketAnother(Model model, @PathVariable("id") Long id, @PathVariable("type") String type , @PathVariable("orderId") Long orderId) {
        model.addAttribute("article", detailService.findById(id, type));
        model.addAttribute("newQuantity", new Quantity());
        List<Quantity> quantityList = new ArrayList<>();
        for (int i = 1; i <= detailService.findById(id, type).getStock(); i++) {
            quantityList.add(Quantity.builder().quantity(i).build());
        }
        model.addAttribute("quantityList", quantityList);
        model.addAttribute("order", orderId);
        return "quantitySelectionAnother";
    }

    @PostMapping(value = "/addingAnotherOrProceed/{id}/{type}")
    public String showProceedOrAdd(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @ModelAttribute Quantity quantity) {
        Orders orders = new Orders();
        orderService.newOrder(orders);
        List<Book> bookList = new ArrayList<>();
        List<Game> gamesList = new ArrayList<>();
        List<Lp> lpList = new ArrayList<>();
        if (detailService.findById(id, type).getClass() == BookFiction.class) {
            BookFiction book = (BookFiction) detailService.findById(id, type);
            book.setOrderQuantity(quantity.getQuantity());
            orderService.addBookFictionToOrder( book, orders.getId());
        } else if (detailService.findById(id, type).getClass() == BookNonFiction.class) {
            BookNonFiction book = (BookNonFiction) detailService.findById(id, type);
            book.setOrderQuantity(quantity.getQuantity());
            orderService.addBookNonFictionToOrder(book, orders.getId());
        } else if (detailService.findById(id, type).getClass() == Game.class) {
            Game game = (Game) detailService.findById(id, type);
            game.setOrderQuantity(quantity.getQuantity());
            orderService.addGameToOrder(game, orders.getId());
        } else {
            Lp lp = (Lp) detailService.findById(id, type);
            lp.setOrderQuantity(quantity.getQuantity());
            orderService.addLpListToOrder(lpList  , orders.getId());
        }


        model.addAttribute("order", orders);
        model.addAttribute("article", detailService.findById(id, type));
        return "addAnotherOrNot";
    }

    @PostMapping(value = "/addingAnotherOrProceedAnother/{id}/{type}/{orderId}")
    public String showProceedOrAddAnother(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("orderId") Long orderId , @ModelAttribute Quantity quantity) {
        List<Book> bookList = new ArrayList<>();
        List<Game> gamesList = new ArrayList<>();
        List<Lp> lpList = new ArrayList<>();
        if (detailService.findById(id, type).getClass() == BookFiction.class) {
            BookFiction book = (BookFiction) detailService.findById(id, type);
            book.setOrderQuantity(quantity.getQuantity());
            orderService.addBookFictionToOrder( book, orderId);
        } else if (detailService.findById(id, type).getClass() == BookNonFiction.class) {
            BookNonFiction book = (BookNonFiction) detailService.findById(id, type);
            book.setOrderQuantity(quantity.getQuantity());
            orderService.addBookNonFictionToOrder(book, orderId);
        } else if (detailService.findById(id, type).getClass() == Game.class) {
            Game game = (Game) detailService.findById(id, type);
            game.setOrderQuantity(quantity.getQuantity());
            orderService.addGameToOrder(game, orderId);
        } else {
            Lp lp = (Lp) detailService.findById(id, type);
            lp.setOrderQuantity(quantity.getQuantity());
            orderService.addLpListToOrder(lpList  , orderId);
        }

        model.addAttribute("order",orderId);
        model.addAttribute("article", detailService.findById(id, type));
        return "addAnotherOrNotAnother";
    }

    @GetMapping(value = "/allArticlesClientAddAnother/{orderId}")
    public String show(Model model, @PathVariable("orderId") Long orderId) {
        model.addAttribute("allArticles" , articleService.getAll());
        model.addAttribute("order" , orderId);
        return "allArticlesClientAddAnotherArticle";
    }

    @GetMapping(value = "/detailedArticleClientAddAnother/{id}/{type}/{orderId}")
    public String showDetailsPageClient(Model model, @PathVariable("id") Long id , @PathVariable("type") String type ,  @PathVariable("orderId") Long orderId) {
        model.addAttribute("lp", detailService.findById(id , type));
        if (detailService.findById(id , type).getClass() == BookFiction.class || detailService.findById(id , type).getClass() == BookNonFiction.class) {
            Book book = (Book) detailService.findById(id , type);
            if(book.getType().equals("FICTION")){
                model.addAttribute("order" , orderId);
                BookFiction bookfiction = (BookFiction) book;
                model.addAttribute("book" , bookfiction);

                return "clientDetailsBookFictionAnother";
            }else {
                BookNonFiction bookNonFiction = (BookNonFiction) book;
                model.addAttribute("order" , orderId);
                model.addAttribute("book" , bookNonFiction);
                model.addAttribute("subject" , bookNonFiction.getSubject());
                return "clientDetailsBookAnother";
            }
        }
        else if (detailService.findById(id , type).getClass() == Game.class) {
            model.addAttribute("order" , orderId);
            Game game = (Game) detailService.findById(id , type);
            model.addAttribute("game" , game);
            return "clientDetailsGameAnother";
        }else {
            Lp lp = (Lp) detailService.findById(id , type);
            model.addAttribute("order" , orderId);
            model.addAttribute("lp" , lp);
            return "clientDetailsLpAnother";
        }
    }

    @GetMapping(value = "/allArticlesClient/{orderId}")
    public String showArticlesAsClient(Model model , @PathVariable("orderId") Long orderId) {
        orderService.remove(orderId);
        return "allArticlesClient";
    }
//
//    @PostMapping(value = "")
}
