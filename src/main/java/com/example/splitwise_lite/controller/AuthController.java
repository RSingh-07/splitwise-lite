package com.example.splitwise_lite.controller;

import com.example.splitwise_lite.dto.LoginRequest;
import com.example.splitwise_lite.dto.SignupRequest;
import com.example.splitwise_lite.entity.Role;
import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.repository.UserRepository;
import com.example.splitwise_lite.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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


    @PostMapping("/auth/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest){
        User userName = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email or password is incorrect!!"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), userName.getPassword())){
            throw new UsernameNotFoundException("Email or password is incorrect!");
        }
        return jwtUtil.generateToken(userName);
    }


}
