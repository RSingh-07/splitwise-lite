package com.example.splitwise_lite.controller;


import com.example.splitwise_lite.dto.ExpenseRequest;
import com.example.splitwise_lite.entity.Expense;
import com.example.splitwise_lite.entity.ExpenseSplit;
import com.example.splitwise_lite.entity.Group;
import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.exception.ExpenseNotFoundException;
import com.example.splitwise_lite.exception.GroupNotFoundException;
import com.example.splitwise_lite.exception.UserNotFoundException;
import com.example.splitwise_lite.repository.ExpenseRepository;
import com.example.splitwise_lite.repository.ExpenseSplitRepository;
import com.example.splitwise_lite.repository.GroupRepository;
import com.example.splitwise_lite.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;

    public ExpenseController(GroupRepository groupRepository, UserRepository userRepository, ExpenseRepository expenseRepository, ExpenseSplitRepository expenseSplitRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.expenseSplitRepository = expenseSplitRepository;
    }

    @PostMapping("/expense")
    public Expense addExpense(@RequestBody ExpenseRequest expenseRequest) {
        Group group = groupRepository.findById(expenseRequest.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + expenseRequest.getGroupId()));  //FETCH GROUP

        User user = userRepository.findById(expenseRequest.getPaidBy())
                .orElseThrow(() -> new UserNotFoundException("User not Found with id: " + expenseRequest.getPaidBy()));   //FETCH THE PAYER


        Expense expense = new Expense(group, user, expenseRequest.getAmount(), expenseRequest.getDescription(), expenseRequest.getExpenseDate());

        Expense savedExpense = expenseRepository.save(expense);   //BUILD AND SAVE EXPENSE ITSELF


        for (Long participantId : expenseRequest.getParticipantIds()) {  //GENERATES ONE EXPENSE SPLIT PER PARTICIPANT
            User participant = userRepository.findById(participantId)
                    .orElseThrow(() -> new UserNotFoundException("User not Found with id: " + participantId));

            double shareAmount = expenseRequest.getAmount() / expenseRequest.getParticipantIds().size();

            ExpenseSplit expenseSplit = new ExpenseSplit(savedExpense, participant, shareAmount);
            expenseSplitRepository.save(expenseSplit);
        }
        return savedExpense;
    }

    @GetMapping("/expense")
    public List<Expense> getAllExpense(){
        List<Expense> getExpenses = expenseRepository.findAll();
        return getExpenses;
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        Expense expenseById = expenseRepository.findById(id)
                .orElseThrow(() ->new ExpenseNotFoundException("No Expense exists with id: "+id));

        return expenseById;
    }

    @DeleteMapping("/expense/{id}")
    public String deleteExpense(@PathVariable Long id){
        Expense expenseById = expenseRepository.findById(id)
                .orElseThrow(() ->new ExpenseNotFoundException("No Expense exists with id: "+id));
        expenseRepository.delete(expenseById);

        return "Deleted expense Succcessfully!";
    }


}
