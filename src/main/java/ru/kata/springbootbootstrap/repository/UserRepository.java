package ru.kata.springbootbootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.springbootbootstrap.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

    User findByUsername(String username);
}
