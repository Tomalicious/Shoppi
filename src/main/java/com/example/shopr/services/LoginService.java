package com.example.shopr.services;


import com.example.shopr.domain.User;
import com.example.shopr.repositories.LoginRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Getter
    private static boolean isLogged;

    public boolean login(String userName , String password){
        isLogged = userName.equals(password);
        return isLogged;
    }

    public List<User> getUsers(){
        return loginRepository.getUsers();
    }
}
