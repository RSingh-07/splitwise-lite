package com.example.splitwise_lite.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "expense_groups")
public class Group{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Expense> expense;

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