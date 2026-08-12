package com.example.splitwise_lite.dto;

import jakarta.validation.constraints.NotNull;

public class GroupMemberRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long groupId;
    private String role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
