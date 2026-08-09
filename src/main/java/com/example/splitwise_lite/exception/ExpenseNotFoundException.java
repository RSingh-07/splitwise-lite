package com.example.splitwise_lite.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException (String message){
        super(message);
    }
}
