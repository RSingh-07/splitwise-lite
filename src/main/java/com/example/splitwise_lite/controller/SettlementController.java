package com.example.splitwise_lite.controller;

import com.example.splitwise_lite.entity.Group;
import com.example.splitwise_lite.exception.GroupNotFoundException;
import com.example.splitwise_lite.repository.GroupRepository;
import com.example.splitwise_lite.service.SettlementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettlementController {
    private final SettlementService settlementService;
    private final GroupRepository groupRepository;

    public SettlementController(SettlementService settlementService, GroupRepository groupRepository) {
        this.settlementService = settlementService;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/groups/{groupId}/settlement")
    public List<String> getSettlement(@PathVariable Long groupId){
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new GroupNotFoundException("Group not found with id: "+groupId));
        return settlementService.getSettlementPlan(group);
    }
}
