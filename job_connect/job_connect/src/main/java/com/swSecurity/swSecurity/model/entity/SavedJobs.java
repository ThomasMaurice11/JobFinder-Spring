package com.swSecurity.swSecurity.model.entity;

import com.swSecurity.swSecurity.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_jobs")
public class SavedJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saved_job_id", updatable = false)
    private Long savedJobId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private User jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Jobs job;


    public SavedJobs() {
    }

    public Long getSavedJobId() {
        return savedJobId;
    }

    public void setSavedJobId(Long savedJobId) {
        this.savedJobId = savedJobId;
    }

    public User getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(User jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

}
