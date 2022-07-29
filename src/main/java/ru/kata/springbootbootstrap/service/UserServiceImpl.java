package ru.kata.springbootbootstrap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.springbootbootstrap.entity.User;
import ru.kata.springbootbootstrap.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(getEncodedPassword(user));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getPassword().length() == 0) {
            user.setPassword(userRepository.getById(user.getId()).getPassword());
        } else {
            user.setPassword(getEncodedPassword(user));
        }
        userRepository.save(user);
    }

    private String getEncodedPassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
