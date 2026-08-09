package com.example.splitwise_lite.repository;

import com.example.splitwise_lite.entity.Expense;
import com.example.splitwise_lite.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findByExpenseIn(List<Expense> expenses);
}

