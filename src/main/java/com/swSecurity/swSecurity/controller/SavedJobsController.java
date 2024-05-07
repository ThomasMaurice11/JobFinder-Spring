package com.swSecurity.swSecurity.controller;

import com.swSecurity.swSecurity.model.entity.SavedJobs;
import com.swSecurity.swSecurity.service.SavedJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "jobConnect/savedJobs")
public class SavedJobsController {

    private final SavedJobsService savedJobsService;

    @Autowired
    public SavedJobsController(SavedJobsService savedJobsService) {
        this.savedJobsService = savedJobsService;
    }

    @GetMapping
    public List<SavedJobs> getSavedJobs(){
        return savedJobsService.getSavedJobs();
    }

    @PostMapping
    public ResponseEntity<SavedJobs> saveJob(@RequestBody SavedJobs savedJob){
        return savedJobsService.saveJob(savedJob);
    }

    @DeleteMapping(path = "{saved_job_id}")
    public void deleteSavedJob(@PathVariable("saved_job_id") Long savedJobId) {
        savedJobsService.deleteSavedJob(savedJobId);
    }
}
