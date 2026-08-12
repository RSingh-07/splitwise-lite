package com.example.splitwise_lite.controller;


import com.example.splitwise_lite.entity.Group;
import com.example.splitwise_lite.repository.GroupRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.splitwise_lite.exception.GroupNotFoundException;

import java.util.List;

@RestController
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

     @GetMapping("/groups")
    public List<Group> getGroups(){
        List<Group> getAllGroups = groupRepository.findAll();
        return getAllGroups;
     }

     @GetMapping("/groups/{id}")
    public Group getGroupById(@PathVariable Long id){
        Group getGroupById = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group Id is not found!!"));
        return getGroupById;
     }

     @PostMapping("/groups")
    public Group addGroup(@Valid @RequestBody Group group){
        Group groupDetails = groupRepository.save(group);
        return groupDetails;
     }

     @PutMapping("/groups/{id}")
    public Group editGroup(@PathVariable Long id,@Valid @RequestBody Group group){
         Group getGroupById = groupRepository.findById(id)
                 .orElseThrow(() -> new GroupNotFoundException("Group Id is not found!!"));
         getGroupById.setName(group.getName());

         Group editedGroup = groupRepository.save(getGroupById);
         return editedGroup;
     }


     @DeleteMapping("/groups/{id}")
    public String deletedGroup(@PathVariable Long id){
         Group getGroupById = groupRepository.findById(id)
                 .orElseThrow(() -> new GroupNotFoundException("Group Id is not found!!"));
         groupRepository.delete(getGroupById);
         return "Group deleted Successfully!!";
     }
}
