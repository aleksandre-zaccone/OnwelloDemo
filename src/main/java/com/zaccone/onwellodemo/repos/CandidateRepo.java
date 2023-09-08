package com.zaccone.onwellodemo.repos;

import com.zaccone.onwellodemo.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

    boolean existsByFirstNameAndLastName(String firstName, String lastname);



    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.votes WHERE c.id = :candidateId")
    Optional<Candidate> getCandidateWithVotes(@Param("candidateId") Long candidateId);

    @Query("SELECT COUNT(v) FROM Candidate c JOIN c.votes v WHERE c.id = :candidateId")
    Long getVoteCountForCandidate(@Param("candidateId") Long candidateId);

}
