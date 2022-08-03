package ru.kata.springbootbootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.springbootbootstrap.service.UserService;

@Controller
@RequestMapping("/")
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "user")
    public String getCurrentUser(@AuthenticationPrincipal UserDetails user, ModelMap model) {
        model.addAttribute("user", userService.findByUsername(user.getUsername()));
        model.addAttribute("currentuser", user);
        return "user";
    }
}
