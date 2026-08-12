package com.example.splitwise_lite.controller;


import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.exception.UserNotFoundException;
import com.example.splitwise_lite.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/users")
    public List<User> getUser(){
        List<User> usersList = userRepository.findAll();
        return usersList;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException("User not found with id: "+id));
        return user;
    }


    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user){
        User userDetails =  userRepository.save(user);
        return userDetails;
    }

    @PutMapping("/users/{id}")
    public User editUserDetails(@PathVariable Long id, @Valid @RequestBody User user){
        User userToBeEdited = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+id));

        userToBeEdited.setName(user.getName());
        userToBeEdited.setEmail(user.getEmail());

        User editedUser = userRepository.save(userToBeEdited);
        return editedUser;

    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        User userToBeDeleted = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+id));

    userRepository.delete(userToBeDeleted);

        return "User Deleted Successfully";
    }

}
