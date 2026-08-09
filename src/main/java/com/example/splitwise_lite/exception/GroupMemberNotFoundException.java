package com.example.splitwise_lite.exception;

public class GroupMemberNotFoundException extends RuntimeException{
    public GroupMemberNotFoundException(String message){
        super(message);
    }
}
