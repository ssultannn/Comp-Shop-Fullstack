package com.shop.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.demo.Service.UserService;
import com.shop.demo.entity.User;
import com.shop.demo.request.LoginRequest;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String result = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (result.equals("Username does not exist!") || result.equals("Invalid password!")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        userService.logoutUser(); // Реализуйте этот метод в UserService
        return ResponseEntity.ok("Logout successful!");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getUserStatus() {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser.getUsername());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

}
