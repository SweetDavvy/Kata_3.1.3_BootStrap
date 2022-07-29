package ru.kata.springbootbootstrap.service;

import ru.kata.springbootbootstrap.entity.Role;
import ru.kata.springbootbootstrap.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    void saveRole(Role role);

    void getUserRoles(User user);

    Role applyRole(Role role);
}
