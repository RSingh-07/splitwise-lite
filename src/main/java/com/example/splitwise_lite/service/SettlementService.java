package com.example.splitwise_lite.service;

import com.example.splitwise_lite.entity.Expense;
import com.example.splitwise_lite.entity.ExpenseSplit;
import com.example.splitwise_lite.entity.Group;
import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.repository.ExpenseRepository;
import com.example.splitwise_lite.repository.ExpenseSplitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettlementService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository expenseSplitRepository;

    public SettlementService(ExpenseRepository expenseRepository, ExpenseSplitRepository expenseSplitRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseSplitRepository = expenseSplitRepository;
    }


    public HashMap<User, Double> calculateNetBalances(Group group){
        List<Expense> allExpense = expenseRepository.findByGroup(group);
        HashMap<User, Double> addBalance = new HashMap<>();
        for(Expense expense: allExpense){
           if(addBalance.containsKey(expense.getPaidBy())){
               addBalance.put(expense.getPaidBy(),  addBalance.get(expense.getPaidBy()) + expense.getAmount());
           }else {
               addBalance.put(expense.getPaidBy(), expense.getAmount());
           }
        }
        List<ExpenseSplit> allSplits = expenseSplitRepository.findByExpenseIn(allExpense);
        for(ExpenseSplit expenseSplit :allSplits){
            if(addBalance.containsKey(expenseSplit.getUser())){
                addBalance.put(expenseSplit.getUser(), addBalance.get(expenseSplit.getUser()) - expenseSplit.getShareAmount());
            }else{
                addBalance.put(expenseSplit.getUser(),-expenseSplit.getShareAmount());
            }
        }
        return addBalance;
    }

    public List<String> getSettlementPlan(Group group){
        HashMap<User, Double> balances = calculateNetBalances(group);
        List<Map.Entry<User, Double>> balanceList = new ArrayList<>(balances.entrySet());
        balanceList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        List<String> transactions = new ArrayList<>();

        //greedy loop
        int left =0;
        int right = balanceList.size()-1;

        return transactions;
    }
}
