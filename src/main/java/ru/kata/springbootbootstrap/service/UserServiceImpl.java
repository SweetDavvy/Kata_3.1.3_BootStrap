package ru.kata.springbootbootstrap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.springbootbootstrap.entity.User;
import ru.kata.springbootbootstrap.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    final
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        user.setPassword(getEncodedPassword(user));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        if(user.getPassword().length() == 0) {
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
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exist!");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),user.getPassword(),user.getAuthorities());
    }

}
