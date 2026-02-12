package com.product.web.controller;

import com.product.model.User;
import com.product.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Serves the main landing page.
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Serves the universal login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Serves the user registration/sign-up page.
     */
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    /**
     * Handles the user registration logic.
     */
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        try {
            userService.createUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
        return "redirect:/login?registered";
    }
}