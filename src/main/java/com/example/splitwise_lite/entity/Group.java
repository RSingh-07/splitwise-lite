package com.example.splitwise_lite.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "expense_groups")
public class Group{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Group(){}
    public Group(String name){this.name = name;}

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name= name;
    }
}