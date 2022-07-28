package ru.kata.springbootbootstrap.controller;

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
@RequestMapping
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.findByUsername(user.getUsername()));
        model.addAttribute("user", user);
        getUserRoles(user);
        model.addAttribute("roles");

        return "admin/adminPage";
    }

    @GetMapping("/add")
    public String newUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        getUserRoles(user);
        model.addAttribute("roles");
        return "admin/newUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles");
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(this::applyRoles)
                .collect(Collectors.toSet()));
    }

    private Role applyRoles(Role role) {
        return roleService.getById(role.getId());
    }

}
