package com.swSecurity.swSecurity.model.entity;

import com.swSecurity.swSecurity.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "proposals")
public class Proposals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id", updatable = false)
    private Long proposalId;
    @Column(name = "status", nullable = false)
    private String ProposalStatus = "Pending";

    @OneToOne
    @JoinColumn(name = "job_id")
    private Jobs job;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User senderId;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiverId;

    private String SenderName;
    private String DescribeYourself;



    public Proposals() {
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Jobs getJob() {
        return job;
    }

    public void setJob(Jobs job) {
        this.job = job;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getDescribeYourself() {
        return DescribeYourself;
    }

    public void setDescribeYourself(String describeYourself) {
        DescribeYourself = describeYourself;
    }

    public String getProposalStatus() {
        return ProposalStatus;
    }

    public void setProposalStatus(String proposalStatus) {
        ProposalStatus = proposalStatus;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(User receiverId) {
        this.receiverId = receiverId;
    }

}
