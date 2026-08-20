package com.example.splitwise_lite.controller;

import com.example.splitwise_lite.dto.SignupRequest;
import com.example.splitwise_lite.entity.Role;
import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/auth/register")
    public User register(@Valid @RequestBody SignupRequest signupRequest){
       User user = new User();
       user.setName(signupRequest.getName());
       user.setEmail(signupRequest.getEmail());
       user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
       user.setRole(Role.USER);
       userRepository.save(user);
       return user;

    }


}
