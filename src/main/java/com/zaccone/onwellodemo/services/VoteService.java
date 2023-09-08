package com.zaccone.onwellodemo.services;

import com.zaccone.onwellodemo.domain.Vote;
import com.zaccone.onwellodemo.domain.Voter;
import com.zaccone.onwellodemo.exceptions.exceptions.voteExceptions.VoteNotFoundException;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterVotedAlready;
import com.zaccone.onwellodemo.repos.VoteRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepo voteRepo;
    private final VoterService voterService;

    @Autowired
    public VoteService(VoteRepo voteRepo, VoterService voterService) {
        this.voteRepo = voteRepo;
        this.voterService = voterService;
    }

    @Transactional
    public void addVote(Vote vote) {
        Voter existedVoter = voterService.getVoterById(vote.getVoter().getId());
        if (existedVoter.isVoted()){
            throw new VoterVotedAlready("This voter voted already !!!");
        }
        existedVoter.setVoted(true);
        voterService.updateVoterDetails(existedVoter);

        vote.setCreatedAt(LocalDateTime.now());
        voteRepo.save(vote);
    }

    public List<Vote> getAllVotes(){
       List<Vote> allVotes = voteRepo.findAll();
       if(allVotes.isEmpty()){
           throw new VoteNotFoundException("Votes not found in DB");
       }
       else{
           return allVotes;
       }
    }

    public List<Vote> getAllVotesByCandidateId(Long candidateId) {
        List<Vote> candidateVotes =  voteRepo.findAllByCandidateId(candidateId);
        if(candidateVotes.isEmpty()){
            throw new VoteNotFoundException("Votes for a candidate with id: " + candidateId + "not found.");
        }
        else{
            return candidateVotes;
        }
    }
}
