package com.swSecurity.swSecurity.controller;

import com.swSecurity.swSecurity.model.entity.Proposals;
import com.swSecurity.swSecurity.service.ProposalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "jobConnect/proposals")
public class ProposalsController {

    private final ProposalsService proposalsService;

    @Autowired
    public ProposalsController(ProposalsService proposalsService) {
        this.proposalsService = proposalsService;
    }

    @GetMapping
    public List<Proposals> getProposals(){
        return proposalsService.getProposals();
    }


    @GetMapping("/receiverId/{receiverId}")
    public List<Proposals> getProposalsByReceiverId(@PathVariable Long receiverId) {
        List<Proposals> proposalsByReceiverId = proposalsService.getProposalsByReceiverId(receiverId);
        return proposalsByReceiverId;
    }

    @PostMapping
    public ResponseEntity<Proposals> createProposal(@RequestBody Proposals proposal){
        return proposalsService.createProposal(proposal);
    }

    @PutMapping("/accept/{proposalId}")
    public ResponseEntity<Proposals> acceptProposal(@PathVariable Long proposalId) {
        Proposals updatedProposal = proposalsService.acceptProposal(proposalId);
        return ResponseEntity.ok(updatedProposal);
    }

    @PutMapping("/reject/{proposalId}")
    public ResponseEntity<Proposals> rejectProposal(@PathVariable Long proposalId) {
        Proposals updatedProposal = proposalsService.rejectProposal(proposalId);
        return ResponseEntity.ok(updatedProposal);
    }

    @DeleteMapping(path = "{proposalId}")
    public void deleteProposal(@PathVariable("proposalId") Long proposalId) {
        proposalsService.deleteProposal(proposalId);
    }
}
