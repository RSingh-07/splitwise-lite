package com.example.splitwise_lite.entity;
import jakarta.persistence.*;

@Entity
public class GroupMember{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    private String role;


    public GroupMember(){}

    public GroupMember(User user, Group group,String role){
        this.user=user;
        this.role=role;
        this.group=group;
    }

    public Long getId(){return id;}

    public User getUser(){return user;}

    public Group getGroup(){return group;}

    public String getRole(){return role;}


    public void setId(Long id){this.id=id;}

    public void setUser(User user){this.user=user;}

    public void setGroup(Group group){this.group=group;}

    public void setRole(String role){this.role=role;}



}
