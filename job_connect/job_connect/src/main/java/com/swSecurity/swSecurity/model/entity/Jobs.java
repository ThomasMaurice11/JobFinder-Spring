package com.swSecurity.swSecurity.model.entity;


import com.swSecurity.swSecurity.user.User;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;




@Entity
@Table(name = "jobs")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", updatable = false)
    private Long jobId;
    @Column(name = "job_title" , nullable = false)
    private String jobTitle;
    @Column(name = "job_type" , nullable = false)
    private String jobType;
    @Column(name = "job_budget" , nullable = false )
    private BigDecimal jobBudget;
    @Column(name = "post_date" , nullable = false)
    private LocalDate postDate;
    @Column(name = "iob_description" , nullable = false)
    private String jobDescription;
    @Column(name = "number_of_proposals" , nullable = false)
    private Integer numberOfProposals;

    @ManyToOne
    @JoinColumn(name = "id")
    private User employer;

    @OneToMany(mappedBy = "job")
    private List<SavedJobs> savedJob;


    public Jobs() {
    }


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public BigDecimal getJobBudget() {
        return jobBudget;
    }

    public void setJobBudget(BigDecimal jobBudget) {
        this.jobBudget = jobBudget;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getNumberOfProposals() {
        return numberOfProposals;
    }

    public void setNumberOfProposals(Integer numberOfProposals) {
        this.numberOfProposals = numberOfProposals;
    }

    public User getEmployer() {
        return employer;
    }

    public void setEmployer(User employer) {
        this.employer = employer;
    }

}


