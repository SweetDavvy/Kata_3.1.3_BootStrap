package ru.kata.springbootbootstrap.service;

import org.springframework.stereotype.Component;
import ru.kata.springbootbootstrap.entity.Role;
import ru.kata.springbootbootstrap.entity.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@Component
public class UserPostCreate {

    private final UserService userService;
    private final RoleService roleService;

    private UserPostCreate(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        roleService.saveRole(new Role(1L, "ADMIN"));
        roleService.saveRole(new Role(2L, "USER"));
        userService.saveUser(new User("admin", "admin",
                new HashSet<>(roleService.findAllRoles()),
                "David", "Pervakov", 27));
    }
}
