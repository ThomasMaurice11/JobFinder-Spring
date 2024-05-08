package com.swSecurity.swSecurity.model.repository;

import com.swSecurity.swSecurity.model.entity.Proposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalsRepository extends JpaRepository<Proposals, Long> {
    List<Proposals> findByReceiverId_ID(Long receiverId);
}