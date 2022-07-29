package ru.kata.springbootbootstrap.service;

import ru.kata.springbootbootstrap.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(User user);

    void deleteById(Long id);

    void updateUser(User user);

    User findByUsername(String username);

    List<User> findAllUsers();
}
