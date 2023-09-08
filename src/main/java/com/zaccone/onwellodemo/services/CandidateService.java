package com.zaccone.onwellodemo.services;

import com.zaccone.onwellodemo.domain.Candidate;
import com.zaccone.onwellodemo.exceptions.exceptions.candidateExceptions.CandidateAlreadyRegisteredException;
import com.zaccone.onwellodemo.exceptions.exceptions.candidateExceptions.CandidateNotFoundException;
import com.zaccone.onwellodemo.repos.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    private final CandidateRepo candidateRepo;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }

    public Candidate saveCandidate(Candidate candidate) throws CandidateAlreadyRegisteredException {
        if (candidateRepo.existsByFirstNameAndLastName(candidate.getFirstName(), candidate.getLastName())) {
            throw new CandidateAlreadyRegisteredException("Candidate with first name " + candidate.getFirstName() +
                    " and last name " + candidate.getLastName() + " already exists");
        }
        return candidateRepo.save(candidate);
    }



    public List<Candidate> getCandidatessAsList() throws CandidateNotFoundException {
        List<Candidate> allCandidates = candidateRepo.findAll();
        if (allCandidates.isEmpty()) {
            throw new CandidateNotFoundException("No Candidates found in DB");
        } else {
            return allCandidates;
        }
    }


    public Candidate getCandidateById(Long id) throws CandidateNotFoundException{
        Optional<Candidate> existingCandidate = candidateRepo.getCandidateWithVotes(id);
        if(existingCandidate.isEmpty()){
            throw new CandidateNotFoundException("Candidate with id: " + " " + id + " not found in DB");
        }
        else {
            return existingCandidate.get();
        }
    }

    public Candidate updateCandidateDetails(Candidate candidate) throws CandidateNotFoundException{
        Candidate existingCandidate = getCandidateById(candidate.getId());
        return candidateRepo.save(candidate);
    }


    public Long getVoteCountForCandidate(Long candidateId) {
        Candidate existingCandidate = getCandidateById(candidateId);
        return (long) existingCandidate.getVotes().size();
    }
}
