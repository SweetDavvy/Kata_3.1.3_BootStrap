package ru.kata.springbootbootstrap;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kata.springbootbootstrap.entity.Role;
import ru.kata.springbootbootstrap.entity.User;
import ru.kata.springbootbootstrap.service.RoleService;
import ru.kata.springbootbootstrap.service.UserService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootBootstrapApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringBootBootstrapApplication.class, args);
        ApplicationContext context = SpringApplication.run(SpringBootBootstrapApplication.class, args);
        try {
            UserService userService = context.getBean(UserService.class);
            RoleService roleService = context.getBean(RoleService.class);

            Role roleAdmin = new Role("ADMIN");
            Role roleUser = new Role("USER");
            Set<Role> adminSet = new HashSet<>();
            Set<Role> userSet = new HashSet<>();

            roleService.saveRole(roleAdmin);
            roleService.saveRole(roleUser);

            adminSet.add(roleAdmin);
            adminSet.add(roleUser);
            userSet.add(roleUser);

            User newUser = new User("Sergey", "Wagov", 28, "wagov@mail.com", "User",
                    "user", userSet);
            User admin = new User("David", "Pervakov", 27, "sweetdavvy@gmail.com", "admin",
                    "admin", adminSet);

            userService.saveUser(newUser);
            userService.saveUser(admin);

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}

