package com.example.splitwise_lite.repository;

import com.example.splitwise_lite.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
}
