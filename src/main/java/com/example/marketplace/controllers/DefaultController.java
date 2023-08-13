package com.example.marketplace.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class DefaultController {

    public String index() {
        return "index";
    }
}
