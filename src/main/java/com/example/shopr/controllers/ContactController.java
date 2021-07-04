package com.example.shopr.controllers;


import com.example.shopr.domain.User;
import com.example.shopr.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contactpage")
    public String showContactPage(Model model) {
        model.addAttribute("newContact" , new User());
        return "contact";
    }

    @PostMapping(value = "/sendEmail")
    public String sendEmail(@ModelAttribute User contact){
        contactService.sendMail(contact);
        return "redirect:/";
    }

    @GetMapping(value="/signUp")
    public String signUpPage(){
        return "signUpNow";
    }
}
