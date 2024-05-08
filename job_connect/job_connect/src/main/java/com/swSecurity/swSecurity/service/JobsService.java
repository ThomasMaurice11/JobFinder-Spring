package com.swSecurity.swSecurity.service;

import com.swSecurity.swSecurity.model.entity.Jobs;
import com.swSecurity.swSecurity.model.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;

    @Autowired
    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }


    public List<Jobs> getAllJobs() {
        return jobsRepository.findAll();
    }


    public ResponseEntity<Jobs> getJobById(Long jobId) {
        Jobs job = jobsRepository.findById(jobId)
                .orElseThrow(()-> new IllegalStateException(
                        "job with id " + jobId + " not found"));
        return ResponseEntity.ok(job);
    }

    public ResponseEntity<Jobs> createJob(Jobs job) {
            Jobs createdJob = jobsRepository.save(job);
            return ResponseEntity.ok(createdJob);
    }

    public void deleteJob(Long jobId) {
        boolean exists = jobsRepository.existsById(jobId);
        if (!exists){
            throw new IllegalStateException("job with id "+ jobId +" does not exists");
        }
        jobsRepository.deleteById(jobId);
    }


    public ResponseEntity<Jobs> updateJob(Long jobId , Jobs jobDetails) {

        Jobs job = jobsRepository.findById(jobId)
                .orElseThrow(()-> new IllegalStateException(
                        "job with id " + jobId + " does not exist"));
        job.setJobTitle(jobDetails.getJobTitle());
        job.setJobType(jobDetails.getJobType());
        job.setJobBudget(jobDetails.getJobBudget());
    
        job.setJobDescription(jobDetails.getJobDescription());
        job.setNumberOfProposals(jobDetails.getNumberOfProposals());

        Jobs updatedJob = jobsRepository.save(job);
        return ResponseEntity.ok(updatedJob);
    }
}

