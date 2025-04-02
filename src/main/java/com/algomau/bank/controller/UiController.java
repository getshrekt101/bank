package com.algomau.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UiController {
    @RequestMapping("/clientHome")
    public String clientHome() {
        System.out.println("clientHome request");
        return "clientHome";
    }
    @RequestMapping("/userProfile")
    public String userProfile() {
        System.out.println("userProfile request");
        return "userProfile";
    }

    @RequestMapping("/accountsPage")
    public String accountsPage() {
        System.out.println("accountsPage request");
        return "accountsPage";
    }

    @RequestMapping("/transactionsPage")
    public String transactionsPage() {
        System.out.println("transactionsPage request");
        return "transactionsPage";
    }
}
