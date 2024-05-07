package com.swSecurity.swSecurity.model.repository;


import com.swSecurity.swSecurity.model.entity.SavedJobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedJobsRepository extends JpaRepository<SavedJobs , Long> {
}
