package com.example.splitwise_lite.repository;

import com.example.splitwise_lite.entity.Expense;
import com.example.splitwise_lite.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroup(Group group);
}
