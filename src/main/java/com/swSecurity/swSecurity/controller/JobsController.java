package com.swSecurity.swSecurity.controller;

import com.swSecurity.swSecurity.service.JobsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.jobConnect.service.JobsService;

import com.swSecurity.swSecurity.model.entity.Jobs;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "jobConnect/jobs")
public class JobsController {

    private final JobsService jobsService;

    @Autowired
    public JobsController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping
    public List<Jobs> getAllJobs(){
        return jobsService.getAllJobs();
    }

    @GetMapping("{jobId}")
    public ResponseEntity<Jobs> getJobById(@PathVariable("jobId") Long jobId){
        return jobsService.getJobById(jobId);
    }

    @PostMapping
    public ResponseEntity<Jobs> createJob(@RequestBody Jobs job){

        return jobsService.createJob(job);
    }

    @DeleteMapping(path = "{jobId}")
    public void deleteJob(@PathVariable("jobId") Long jobId) {
        jobsService.deleteJob(jobId);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Jobs> updateJob(@PathVariable Long jobId, @RequestBody Jobs jobDetails) {
        return jobsService.updateJob(jobId, jobDetails);
    }
}
