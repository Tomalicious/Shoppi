package com.example.shopr.services;


import com.example.shopr.domain.Authorization;
import com.example.shopr.domain.User;
import com.example.shopr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public Authorization findAuth(String user) {
        return userRepository.findAuth(user);
    }

    public String findPass(String user) {
        return userRepository.findPass(user);
    }

    public Long findId(String user) {
        return userRepository.findId(user);
    }

    public User findById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void updateUser(User thisUser) {
        userRepository.updateUser(thisUser);
    }

}
