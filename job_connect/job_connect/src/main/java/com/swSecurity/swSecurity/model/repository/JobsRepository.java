package com.swSecurity.swSecurity.model.repository;

import com.swSecurity.swSecurity.model.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs , Long> {
}
