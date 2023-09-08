package com.zaccone.onwellodemo.contollers;

import com.zaccone.onwellodemo.domain.Vote;
import com.zaccone.onwellodemo.domain.Voter;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterVotedAlready;
import com.zaccone.onwellodemo.services.CandidateService;
import com.zaccone.onwellodemo.services.VoteService;
import com.zaccone.onwellodemo.services.VoterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("voteSystem/api/V1/")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    VoterService voterService;

    @Autowired
    CandidateService candidateService;

    @PostMapping("vote/addVote")
    @Operation(summary = "Add a vote", description = "Registers a vote and returns a success message.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Vote successfully registered"
            ),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<String> addVote (@RequestBody Vote vote){
        voteService.addVote(vote);
        return new ResponseEntity<>("Your Vote was registered", HttpStatus.OK);
    }


    @GetMapping("vote/getAllVotes")
    @Operation(summary = "Get all votes", description = "Retrieves a list of all votes.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Votes retrieved successfully",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Vote.class)))
            ),
            @ApiResponse(responseCode = "404", description = "No votes found"),
    })
    public ResponseEntity<List<Vote>> getAllVOtes (){
        log.info("getAllVOtes method called");
        List<Vote> allVotes = voteService.getAllVotes();
        log.info("Found {} voters", allVotes.size());
        return new ResponseEntity<>(allVotes, HttpStatus.OK);
    }

    @Operation(summary = "Get votes by candidate ID", description = "Retrieves votes by candidate ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Votes retrieved successfully",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Vote.class)))
            ),
            @ApiResponse(responseCode = "404", description = "No votes found"),
    })
    @GetMapping("vote/getAllVotesByCandidateId/{candidateId}")
    public ResponseEntity<List<Vote>> getAllVotesByCandidateId(@PathVariable Long candidateId) {
        List<Vote> votes = voteService.getAllVotesByCandidateId(candidateId);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }





}
