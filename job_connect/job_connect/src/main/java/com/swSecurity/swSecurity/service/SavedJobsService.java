package com.swSecurity.swSecurity.service;

import com.swSecurity.swSecurity.model.entity.SavedJobs;
import com.swSecurity.swSecurity.model.repository.SavedJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobsService {

    private final SavedJobsRepository savedJobsRepository;

    @Autowired
    public SavedJobsService(SavedJobsRepository savedJobsRepository) {
        this.savedJobsRepository = savedJobsRepository;
    }

    public List<SavedJobs> getSavedJobs() {
        return savedJobsRepository.findAll();
    }

    public ResponseEntity<SavedJobs> saveJob(SavedJobs savedJob) {
        SavedJobs savedJobs = savedJobsRepository.save(savedJob);
        return ResponseEntity.ok(savedJobs);
    }

    public void deleteSavedJob(Long savedJobId) {
        boolean exists = savedJobsRepository.existsById(savedJobId);
        if (!exists){
            throw new IllegalStateException("job with id "+ savedJobId +" does not exists");
        }
        savedJobsRepository.deleteById(savedJobId);
    }
}
