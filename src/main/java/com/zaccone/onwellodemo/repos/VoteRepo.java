package com.zaccone.onwellodemo.repos;

import com.zaccone.onwellodemo.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {


    @Query("SELECT vote FROM Vote vote WHERE vote.id = :candidateId")
    List<Vote> findAllByCandidateId(@Param("candidateId") Long candidateId);
}
