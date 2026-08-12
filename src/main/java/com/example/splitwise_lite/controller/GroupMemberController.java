package com.example.splitwise_lite.controller;


import com.example.splitwise_lite.dto.GroupMemberRequest;
import com.example.splitwise_lite.entity.Group;
import com.example.splitwise_lite.entity.GroupMember;
import com.example.splitwise_lite.entity.User;
import com.example.splitwise_lite.exception.GroupMemberNotFoundException;
import com.example.splitwise_lite.exception.GroupNotFoundException;
import com.example.splitwise_lite.exception.UserNotFoundException;
import com.example.splitwise_lite.repository.GroupMemberRepository;
import com.example.splitwise_lite.repository.GroupRepository;
import com.example.splitwise_lite.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupMemberController {

    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public GroupMemberController(GroupMemberRepository groupMemberRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping("/group-members")
    public GroupMember addgroupMember(@Valid @RequestBody GroupMemberRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: "+ request.getUserId()));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: "+request.getGroupId()));

        GroupMember newGroupMember = new GroupMember(user, group, request.getRole());

        GroupMember savedGroup = groupMemberRepository.save(newGroupMember);
        return savedGroup;
    }


    @GetMapping("/group-members")
    public List<GroupMember> getGroupMember(){
         List<GroupMember> getGroupMember = groupMemberRepository.findAll();
        return getGroupMember;
    }

    @GetMapping("/group-members/{id}")
    public GroupMember getGroupMemberdetails(@PathVariable Long id){
        GroupMember groupMember = groupMemberRepository.findById(id)
                .orElseThrow(() -> new GroupMemberNotFoundException("Group Member not found with id: "+id));

        return groupMember;
    }

    @DeleteMapping("/group-members/{id}")
    public String deleteGroupMember(@PathVariable Long id){
        GroupMember groupMember = groupMemberRepository.findById(id)
                .orElseThrow(() -> new GroupMemberNotFoundException("Group Member not found with id: "+id));

        groupMemberRepository.delete(groupMember);
        return "Deleted succesfully";
    }



}
