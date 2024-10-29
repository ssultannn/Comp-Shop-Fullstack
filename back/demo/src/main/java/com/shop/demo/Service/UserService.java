package com.shop.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.demo.Repository.UserRepository;
import com.shop.demo.entity.User;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User currentUser; // Поле для хранения текущего пользователя

    public String registerUser(User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        if (existingUser.isPresent()) {
            return "Username already exists!";
        }

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String username, String password) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(username));

        if (existingUser.isEmpty()) {
            return "Username does not exist!";
        }

        User user = existingUser.get();
        if (user.getPassword().equals(password)) {
            currentUser = user; // Устанавливаем текущего пользователя
            return "Login successful!";
        } else {
            return "Invalid password!";
        }
    }

    public void logoutUser() {
        currentUser = null; // Удаляем текущего пользователя
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public String getUsername() {
        return currentUser != null ? currentUser.getUsername() : null;
    }
}
