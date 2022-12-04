package com.aviatickets.aviatickets.controllers;

import com.aviatickets.aviatickets.models.User;
import com.aviatickets.aviatickets.serivce.CustomUserDetailsService;
import com.aviatickets.aviatickets.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

//        userValidator.validate(user, bindingResult); //todo

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.createUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }



}
