package com.example.shopr.controllers;
import com.example.shopr.domain.*;
import com.example.shopr.services.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasketController{

    @Autowired
    private UserService userService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/shopArticle/{id}/{type}/{userId}")
    public String showBasketAmount(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("userId") Long userId) {
        model.addAttribute("article", detailService.findById(id, type));
        model.addAttribute("newQuantity", new Quantity());
        List<Quantity> quantityList = new ArrayList<>();
        for (int i = 1; i <= detailService.findById(id, type).getStock(); i++) {
            quantityList.add(Quantity.builder().quantity(i).build());
        }
        model.addAttribute("quantityList", quantityList);
        model.addAttribute("userId", userId);
        return "quantitySelection";
    }

    @GetMapping(value = "/shopArticleAnother/{id}/{type}/{orderId}/{userId}")
    public String showBasketAnother(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId) {

        model.addAttribute("article", detailService.findById(id, type));
        model.addAttribute("newQuantity", new Quantity());
        List<Quantity> quantityList = new ArrayList<>();
        for (int i = 1; i <= detailService.findById(id, type).getStock(); i++) {
            quantityList.add(Quantity.builder().quantity(i).build());
        }
        model.addAttribute("quantityList", quantityList);
        model.addAttribute("orders", orderId);
        model.addAttribute("userId", userId);
        return "quantitySelectionAnother";
    }

    @PostMapping(value = "/addingAnotherOrProceed/{id}/{type}/{userId}")
    public String showProceedOrAdd(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("userId") Long userId, @ModelAttribute Quantity quantity)  {
        Orders orders = new Orders();
        orders.setOrderDate(ZonedDateTime.now());
        orders.setIsPayed(false);
        try {
            orderService.newOrder(orders);
        } catch (ExceededStockAmountException e) {
            orderService.remove(orders);
            e.printStackTrace();
            return "error3";
        }

        try {
            return this.showProceedOrAddAnother(model, id, type, orders.getId(), userId, quantity);
        } catch (Exception e) {
            orderService.remove(orders);
            e.printStackTrace();
            return "error3";
        }
    }

    @PostMapping(value = "/addingAnotherOrProceedAnother/{id}/{type}/{orderId}/{userId}")
    public String showProceedOrAddAnother(Model model, @PathVariable("id") Long id, @PathVariable("type") String type, @PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId, @ModelAttribute Quantity quantity) {
        List<BookNonFiction> bookListNon = new ArrayList<>();
        List<BookFiction> bookList = new ArrayList<>();
        List<Game> gamesList = new ArrayList<>();
        List<Lp> lpList = new ArrayList<>();
        if (orderService.getOrder(orderId).getIsPayed() == false) {
            Orders orders = orderService.getOrder(orderId);
            if (detailService.findById(id, type).getClass() == BookFiction.class) {
                BookFiction book = (BookFiction) detailService.findById(id, type);
                book.setOrderQuantity(quantity);
                bookList.add(book);
                orders.setBookFictionList(bookList);
            } else if (detailService.findById(id, type).getClass() == BookNonFiction.class) {
                BookNonFiction book = (BookNonFiction) detailService.findById(id, type);
                book.setOrderQuantity(quantity);
                bookListNon.add(book);
                orders.setBookNonList(bookListNon);
            } else if (detailService.findById(id, type).getClass() == Game.class) {
                Game game = (Game) detailService.findById(id, type);
                game.setOrderQuantity(quantity);
                gamesList.add(game);
                orders.setGamesList(gamesList);
            } else {
                Lp lp = (Lp) detailService.findById(id, type);
                lp.setOrderQuantity(quantity);
                lpList.add(lp);
                orders.setLpList(lpList);
            }
            User thisUser = userService.findById(userId);
            List<Orders> orderList = thisUser.getOrderList();
            if (orderList.isEmpty()) {
                orderList.add(orders);
            }
            thisUser.setOrderList(orderList);
            userService.updateUser(thisUser);
            try {
                orderService.updateOrder(orders);
            } catch (ExceededStockAmountException e) {
                orderService.remove(orders);
                e.printStackTrace();
                return "error3";
            }
            model.addAttribute("orders", orders.getId());
            model.addAttribute("userId", userId);
            return "addAnotherOrNot";
        } else {
            Orders orders = new Orders();
            if (detailService.findById(id, type).getClass() == BookFiction.class) {
                BookFiction book = (BookFiction) detailService.findById(id, type);
                book.setOrderQuantity(quantity);
                bookList.add(book);
                orders.setBookFictionList(bookList);
            } else if (detailService.findById(id, type).getClass() == BookNonFiction.class) {
                BookNonFiction book = (BookNonFiction) detailService.findById(id, type);
                book.setOrderQuantity(quantity);
                bookListNon.add(book);
                orders.setBookNonList(bookListNon);
            } else if (detailService.findById(id, type).getClass() == Game.class) {
                Game game = (Game) detailService.findById(id, type);
                game.setOrderQuantity(quantity);
                gamesList.add(game);
                orders.setGamesList(gamesList);
            } else {
                Lp lp = (Lp) detailService.findById(id, type);
                lp.setOrderQuantity(quantity);
                lpList.add(lp);
                orders.setLpList(lpList);
            }
            User thisUser = userService.findById(userId);
            List<Orders> orderList = thisUser.getOrderList();
            if (orderList.isEmpty()) {
                orderList.add(orders);
            }
            thisUser.setOrderList(orderList);
            userService.updateUser(thisUser);
            try {
                orderService.updateOrder(orders);
            } catch (ExceededStockAmountException e) {
                orderService.remove(orders);
                e.printStackTrace();
                return "error3";
            }
            model.addAttribute("orders", orders.getId());
            model.addAttribute("userId", userId);
            return "addAnotherOrNot";
        }
    }


    @GetMapping(value = "/allArticlesClientAddAnother/{orderId}/{userId}")
    public String show(Model model, @PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId) {
        model.addAttribute("allArticles", articleService.getAll());
        model.addAttribute("orders", orderId);
        model.addAttribute("userId", userId);
        return "allArticlesClientAddAnotherArticle";
    }

    @GetMapping(value = "/detailedArticleClientAddAnother/{id}/{type}/{orderId}/{userId}")
    public String showDetailsPageClient(Model model, @PathVariable("id") Long id, @PathVariable("type") String
            type, @PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId) {
        model.addAttribute("lp", detailService.findById(id, type));
        if (detailService.findById(id, type).getClass() == BookFiction.class || detailService.findById(id, type).getClass() == BookNonFiction.class) {
            Book book = (Book) detailService.findById(id, type);
            if (book.getType().equals("FICTION")) {
                model.addAttribute("orders", orderId);
                BookFiction bookfiction = (BookFiction) book;
                model.addAttribute("book", bookfiction);
                model.addAttribute("userId", userId);

                return "clientDetailsBookFictionAnother";
            } else {
                BookNonFiction bookNonFiction = (BookNonFiction) book;
                model.addAttribute("orders", orderId);
                model.addAttribute("book", bookNonFiction);
                model.addAttribute("subject", bookNonFiction.getSubject());
                model.addAttribute("userId", userId);
                return "clientDetailsBookAnother";
            }
        } else if (detailService.findById(id, type).getClass() == Game.class) {
            model.addAttribute("orders", orderId);
            Game game = (Game) detailService.findById(id, type);
            model.addAttribute("game", game);
            model.addAttribute("userId", userId);
            return "clientDetailsGameAnother";
        } else {
            Lp lp = (Lp) detailService.findById(id, type);
            model.addAttribute("orders", orderId);
            model.addAttribute("lp", lp);
            model.addAttribute("userId", userId);
            return "clientDetailsLpAnother";
        }
    }

    @GetMapping(value = "/allArticlesClients/{orderId}/{userId}")
    public String showArticlesAsClient(Model model, @PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId) {
        orderService.remove(orderService.getOrder(orderId));
        model.addAttribute("allArticles", articleService.getAll());
        return "allArticlesClient";
    }

    @PostMapping(value = "/paymentPage/{orderId}/{userId}")
    public String showPaymentPage(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId, Model model) {
        List<Double> totalPricePerArticle = new ArrayList<>();
        Orders order = orderService.getOrder(orderId);
        List<Article> articles = articleService.articleListConverter(order);

        List<BookFiction> bookListFiction = new ArrayList<>();
        List<BookNonFiction> bookListNonFiction = new ArrayList<>();
        List<Game> gamesList = new ArrayList<>();
        List<Lp> lpList = new ArrayList<>();
        bookListFiction.addAll(order.getBookFictionList());
        bookListNonFiction.addAll(order.getBookNonList());
        gamesList.addAll(order.getGamesList());
        lpList.addAll(order.getLpList());

        Double totalPerTypeBookFiction = 0D;
        Integer totalAmountBookFiction = 0;
        for (BookFiction b : bookListFiction) {
            totalPerTypeBookFiction += b.getPrice() * b.getOrderQuantity().getQuantity();
            totalPricePerArticle.add(totalPerTypeBookFiction);
            totalAmountBookFiction += b.getOrderQuantity().getQuantity();
        }
        Double totalPerTypeBookNonFiction = 0D;
        Integer totalAmountBookNonFiction = 0;
        for (BookNonFiction b : bookListNonFiction) {
            totalPerTypeBookNonFiction += b.getPrice() * b.getOrderQuantity().getQuantity();
            totalPricePerArticle.add(totalPerTypeBookNonFiction);
            totalAmountBookNonFiction += b.getOrderQuantity().getQuantity();
        }

        Double totalPerTypeGame = 0D;
        Integer totalAmountGames = 0;
        for (Game g : gamesList) {
            totalPerTypeGame += g.getPrice() * g.getOrderQuantity().getQuantity();
            totalPricePerArticle.add(totalPerTypeGame);
            totalAmountGames += g.getOrderQuantity().getQuantity();
        }
        Double totalPerTypeLp = 0D;
        Integer totalAmountLp = 0;
        for (Lp l : lpList) {
            totalPerTypeLp += l.getPrice() * l.getOrderQuantity().getQuantity();
            totalPricePerArticle.add(totalPerTypeLp);
            totalAmountLp += l.getOrderQuantity().getQuantity();
        }

        Double totalPrice;
        for (Article a : articles) {
            totalPrice = a.getPrice() * a.getOrderQuantity().getQuantity();
            totalPricePerArticle.add(totalPrice);
        }
        Double totalPriceOrder = 0D;
        for (Double d : totalPricePerArticle) {
            totalPriceOrder += d;
        }

        model.addAttribute("gamesType" , "GAME");
        model.addAttribute("totalAmountGames" , totalAmountGames);
        model.addAttribute("totalPerTypeGames", totalPerTypeGame);
        model.addAttribute("lpType" , "LP");
        model.addAttribute("totalAmountLp" , totalAmountLp);
        model.addAttribute("totalPerTypeLp", totalPerTypeLp);
        model.addAttribute("bookFictionType" , "FICTION");
        model.addAttribute("totalAmountFiction" , totalAmountBookFiction);
        model.addAttribute("totalPerTypeFiction", totalPerTypeBookFiction);
        model.addAttribute("bookNonFictionType" , "NON FICTION");
        model.addAttribute("totalAmountBookNon" , totalAmountBookNonFiction);
        model.addAttribute("totalPerTypeBookNon", totalPerTypeBookNonFiction);
        model.addAttribute("allArticles", articles);
        model.addAttribute("totalPrices", totalPricePerArticle);
        model.addAttribute("totalOrder", totalPriceOrder);
        model.addAttribute("orders", orderService.getOrder(orderId));
        model.addAttribute("userId", userId);
        model.addAttribute("newUser", new User());
        return "paymentPage";
    }

    @GetMapping(value = "/payed/{orderId}/{userId}")
    public String showPayment(@PathVariable("orderId") Long orderId, @PathVariable("userId") Long userId, Model model) {
        Orders orders = orderService.getOrder(orderId);
        orders.setIsPayed(true);
        orders.setOrderDate(ZonedDateTime.now());
        orders.setUsers(userService.findById(userId));
        orderService.alterStock(orders);
        try {
            orderService.updateOrder(orders);
        } catch (ExceededStockAmountException e) {
            orderService.remove(orders);
            e.printStackTrace();
            return "error3";
        }
        supplierService.notifySuppliers(orders);
        return "forward:/chosenUser2/" + userId;
    }

    @GetMapping(value = "/chosenUser2/{userId}")
    public String splitProgramAfterSale(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("allArticles", articleService.getAll());
        model.addAttribute("userId" , userId);
        return "allArticlesClient";
    }
}
