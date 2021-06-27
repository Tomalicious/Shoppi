package com.example.shopr.services;


import com.example.shopr.domain.BookFiction;
import com.example.shopr.domain.BookNonFiction;
import com.example.shopr.domain.Game;
import com.example.shopr.domain.Lp;
import com.example.shopr.repositories.EditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditService {

    @Autowired
    private EditRepository editRepository;

    public void updateBook(BookFiction bookFiction) {
        editRepository.updateBook(bookFiction);
    }

    public void updateBookNon(BookNonFiction bookNonFiction) {
        editRepository.updateBookNon(bookNonFiction);
    }

    public void updateGame(Game game) {
       editRepository.updateGame(game);
    }

    public void updateLp(Lp lp) {
        editRepository.updateLp(lp);
    }
}
