package com.example.marketplace.controllers;

import com.example.marketplace.model.User;
import com.example.marketplace.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam("avatar-file") MultipartFile avatarFile, User user) {
        userService.createUser(user, avatarFile);
        return "redirect:/login";
    }

    @GetMapping("/users/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user)
                .addAttribute("products", user.getProducts());
        return "user-info";
    }
}
