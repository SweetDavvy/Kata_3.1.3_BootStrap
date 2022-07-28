package ru.kata.springbootbootstrap.service;

import ru.kata.springbootbootstrap.entity.Role;
import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    List<Role> getAllRoles();

    Role getById(Long id);
}
