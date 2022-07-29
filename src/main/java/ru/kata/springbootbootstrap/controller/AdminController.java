package ru.kata.springbootbootstrap.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import ru.kata.springbootbootstrap.entity.Role;
import ru.kata.springbootbootstrap.entity.User;
import ru.kata.springbootbootstrap.service.RoleService;
import ru.kata.springbootbootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String showUsers(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentuser", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return "admin";
    }

    @GetMapping(value = "/new")
    public String newUser(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("currentuser", user);
        model.addAttribute("roles", roleService.findAllRoles());
        return "/new";
    }

    @PostMapping(value = "/new")
    public String saveNewUser(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/new";
        }
        roleService.getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping(value = "/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/edit/{id}";
        }
        roleService.getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

}
