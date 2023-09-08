package com.zaccone.onwellodemo.services;

import com.zaccone.onwellodemo.domain.Voter;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterAlreadyRegisteredException;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterNotFoundException;
import com.zaccone.onwellodemo.repos.VoterRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {

    private final VoterRepo voterRepo;

    @Autowired
    public VoterService(VoterRepo voterRepo) {
        this.voterRepo = voterRepo;
    }


    public Voter saveVoter(Voter voter) throws VoterAlreadyRegisteredException {
        if (voterRepo.existsByFirstNameAndLastName(voter.getFirstName(), voter.getLastName())) {
            throw new VoterAlreadyRegisteredException("Voter with first name " + voter.getFirstName() +
                    " and last name " + voter.getLastName() + " already exists");
        }
        return voterRepo.save(voter);
    }


    public List<Voter> getAllVotersAsList() throws VoterNotFoundException {
        List<Voter> allVoters = voterRepo.findAll();
        if(allVoters.isEmpty()){
            throw  new VoterNotFoundException("No Voters found in DB");
        }
        else{
            return allVoters;
        }
    }

    public Voter getVoterById (Long id) throws VoterNotFoundException{
            Optional<Voter> voter = voterRepo.findById(id);
            if(voter.isEmpty()){
                throw new VoterNotFoundException("Voter with id:"+ " " + id + " not found in DB");
            }
            else{
                return voter.get();
            }
    }

    @Transactional
    public Voter updateVoterDetails (Voter voter) throws VoterNotFoundException{
        Optional<Voter> existingVoter = voterRepo.findById(voter.getId());
        if(existingVoter.isPresent()){
           return voterRepo.save(voter);
        }
        else{
            throw  new VoterNotFoundException("Voter with id:" + " " + voter.getId() + " not found in DB");
        }
    }



}
