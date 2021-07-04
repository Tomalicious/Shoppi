package com.example.shopr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssessmentController {

    @GetMapping(value = "/assessmentPage")
    public String showAssessmentPage(Model model) {

        return "assessments";
    }
}
