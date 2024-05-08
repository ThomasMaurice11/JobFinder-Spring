package com.swSecurity.swSecurity.service;

import com.swSecurity.swSecurity.model.entity.Proposals;
import com.swSecurity.swSecurity.model.repository.ProposalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProposalsService {
    private final ProposalsRepository proposalsRepository;

    @Autowired
    public ProposalsService(ProposalsRepository proposalsRepository) {
        this.proposalsRepository = proposalsRepository;
    }

    public List<Proposals> getProposals() {
        return proposalsRepository.findAll();
    }

    public List<Proposals> getProposalsByReceiverId(Long receiverId) {
        return proposalsRepository.findByReceiverId_ID(receiverId);
    }

    public ResponseEntity<Proposals> createProposal(Proposals proposal) {
        Proposals proposals = proposalsRepository.save(proposal);
        return ResponseEntity.ok(proposals);
    }

    public Proposals acceptProposal(Long proposalId) {
        Proposals proposal = proposalsRepository.findById(proposalId)
                .orElseThrow(()-> new IllegalStateException("proposal with id " + proposalId + " not found"));
        proposal.setProposalStatus("Accepted");
        return proposalsRepository.save(proposal);
    }

    public Proposals rejectProposal(Long proposalId) {
        Proposals proposal = proposalsRepository.findById(proposalId)
                .orElseThrow(()-> new IllegalStateException("proposal with id " + proposalId + " not found"));
        proposal.setProposalStatus("Rejected");
        return proposalsRepository.save(proposal);
    }

    public void deleteProposal(Long proposalId) {
        boolean exists = proposalsRepository.existsById(proposalId);
        if (!exists){
            throw new IllegalStateException("Proposal with id "+ proposalId +" does not exists");
        }
        proposalsRepository.deleteById(proposalId);
    }
}
