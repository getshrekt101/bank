package com.algomau.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // loads index.html from templates/
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

//    @GetMapping("/users")
//    String users() {
//        return "users";
//    }

}
