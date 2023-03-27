package com.example.elonuz.controller;

import com.example.elonuz.domains.User;
import com.example.elonuz.dto.UserCreateDto;
import com.example.elonuz.enums.Role;
import com.example.elonuz.servises.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserCreateDto dto) {
        User user = User.builder()
                .email(dto.email())
                .username(dto.username())
                .password(dto.password())
                .build();
        user.setRole(dto.role().equals("USER") ? Role.USER : Role.EMPLOYER);
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
}






