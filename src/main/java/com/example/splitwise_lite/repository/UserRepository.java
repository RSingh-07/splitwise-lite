package com.example.splitwise_lite.repository;

import com.example.splitwise_lite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
