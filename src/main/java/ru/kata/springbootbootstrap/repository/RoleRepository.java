package ru.kata.springbootbootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.springbootbootstrap.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
