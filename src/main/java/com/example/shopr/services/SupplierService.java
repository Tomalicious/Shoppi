package com.example.shopr.services;


import com.example.shopr.domain.*;
import com.example.shopr.repositories.SupplierRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ArticleService articleService;

    public void notifySuppliers(Orders orders) {
       List<Article> articles = articleService.articleListConverter(orders);
       List<Orders> ordersList = new ArrayList<>();
       for(int i = 1 ; i <= supplierRepository.getMaxId(); i++){
           for(Article a : articles){
               if(supplierRepository.findAll().get(i-1).getSupplierOrders().isEmpty()) {
                   Orders newOrder = new Orders();
                   ordersList.add(newOrder);
               }else{
                   List<Supplier> supplierList = supplierRepository.findAll();
                   for(int j = 1; j <= supplierRepository.getMaxId(); j++){
                       ordersList.add(supplierList.get(j-1).getSupplierOrders().get(0));
                   }
               }
             if(Integer.valueOf(a.getSupplierId()) == i){
                 if(a.getClass() == BookFiction.class){
                     BookFiction bookFiction = (BookFiction) a;
                     List<BookFiction> bookFictionList = new ArrayList<>();
                     bookFictionList.add(bookFiction);
                     Orders order = ordersList.get(i -1);
                     order.setBookFictionList(bookFictionList);
                     ordersList.set(i-1 , order);
                 }else if(a.getClass() == BookNonFiction.class){
                     BookNonFiction bookNonFiction = (BookNonFiction) a;
                     List<BookNonFiction> bookNonFictionList = new ArrayList<>();
                     bookNonFictionList.add(bookNonFiction);
                     Orders order = ordersList.get(i -1);
                     order.setBookNonList(bookNonFictionList);
                     ordersList.set(i-1 , order);

                 }else if(a.getClass() == Game.class){
                     Game game = (Game) a;
                     List<Game> gamesList = new ArrayList<>();
                     gamesList.add(game);
                     Orders order = ordersList.get(i -1);
                     order.setGamesList(gamesList);
                     ordersList.set(i-1 , order);
                 }else{
                     Lp lp = (Lp) a;
                     List<Lp> lpList = new ArrayList<>();
                     lpList.add(lp);
                     Orders order = ordersList.get(i -1);
                     order.setLpList(lpList);
                     ordersList.set(i-1 , order);
                     ordersList.get(i-1).getLpList().add(lp);
                 }
             }
           }
       }
       List<Supplier> suppliers = supplierRepository.findAll();
        for(Supplier s : suppliers){
            s.getSupplierOrders().add(ordersList.get(Integer.valueOf((int) (s.getId() -1))));
            supplierRepository.updateOrderList(s);
        }
    }

//    public void notifySuppliers(Orders order) {
//        order.getLpList();
//        order.getGamesList();
//        order.getBookNonList();
//        order.getBookFictionList();
//    }
}
