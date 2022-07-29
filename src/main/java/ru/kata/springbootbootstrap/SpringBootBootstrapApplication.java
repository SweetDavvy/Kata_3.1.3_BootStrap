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
        SpringApplication.run(SpringBootBootstrapApplication.class, args);
    }
}

