package com.zaccone.onwellodemo.repos;

import com.zaccone.onwellodemo.domain.Candidate;
import com.zaccone.onwellodemo.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepo extends JpaRepository<Voter, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    List<Voter> findAll();


}
