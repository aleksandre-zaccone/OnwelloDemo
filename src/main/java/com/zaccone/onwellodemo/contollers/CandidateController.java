package com.zaccone.onwellodemo.contollers;

import com.zaccone.onwellodemo.domain.Candidate;
import com.zaccone.onwellodemo.domain.Vote;
import com.zaccone.onwellodemo.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("voteSystem/api/V1/")
public class CandidateController {

    @Autowired
    CandidateService candidateService;


    @PostMapping("createCandidate")
    @Operation(summary = "Create a new candidate", description = "Creates a new candidate and returns the created candidate.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidate successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate){
        Candidate savedVCandidate = candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(savedVCandidate, HttpStatus.OK);
    }


    @GetMapping("getCandidates")
    @Operation(summary = "Get all candidates", description = "Retrieves a list of all candidates.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidates retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No candidates found")
    })
    public ResponseEntity<List<Candidate>> getCandidatesAsList (){
        List<Candidate> allCandidates = candidateService.getCandidatessAsList();
        return new ResponseEntity<>(allCandidates, HttpStatus.OK);
    }

    @GetMapping("getCandidateById/{id}")
    @Operation(summary = "Get a candidate by ID", description = "Retrieves a candidate by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidate retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Candidate not found")
    })
    public ResponseEntity<Candidate> getCandidateById (@PathVariable Long id){
        Candidate candidate = candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @Operation(summary = "Update candidate details", description = "Updates the details of an existing candidate.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candidate details updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Candidate not found")
    })
    @PutMapping("updateCandidate")
    public ResponseEntity<Candidate> updateCandidateDetails(@RequestBody Candidate candidate){
        Candidate updatedCandidate = candidateService.updateCandidateDetails(candidate);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    @Operation(summary = "Get the count of votes by candidate ID", description = "Retrieves the count of votes by candidate ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Vote count retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "integer"))
            ),
            @ApiResponse(responseCode = "404", description = "Candidate not found"),
    })
    @GetMapping("getVoteCountByCandidateId/{candidateId}")
    public ResponseEntity<Long> getVoteCountForCandidate(@PathVariable Long candidateId) {
        Long voteCount = candidateService.getVoteCountForCandidate(candidateId);
        return new ResponseEntity<>(voteCount, HttpStatus.OK);
    }

}
