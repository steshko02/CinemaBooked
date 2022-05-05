package com.example.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(path = "/page")
public class PageController {

    @GetMapping("/moviePage")
    public String moviePage(){
        return "movie";
    }

    @GetMapping("/home")
    public String homePage(){
        return "index";
    }
}
