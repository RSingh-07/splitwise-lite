package com.example.splitwise_lite.entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Expense{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name="paid_by")
    private User paidBy;
    private double amount;
    private String description;
    private LocalDate date;
    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExpenseSplit> expenseSplits;

    public Expense(){}
    public Expense(Group group, User paidBy,double amount, String description,LocalDate date ){
        this.group=group;
        this.paidBy=paidBy;
        this.amount= amount;
        this.description=description;
        this.date=date;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public User getPaidBy() {
        return paidBy;
    }
    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
