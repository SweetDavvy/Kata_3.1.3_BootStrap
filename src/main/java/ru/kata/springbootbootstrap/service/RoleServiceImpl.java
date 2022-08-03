package ru.kata.springbootbootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.springbootbootstrap.entity.Role;
import ru.kata.springbootbootstrap.entity.User;
import ru.kata.springbootbootstrap.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
    @Override
    public void getUserRoles(User user){
        user.setRoles(user.getRoles().stream()
                .map(this::applyRole).collect(Collectors.toSet()));
    }
    @Override
    public Role applyRole(Role role){
        return roleRepository.getById(role.getId());
    }


}
