package com.zaccone.onwellodemo.contollers;

import com.zaccone.onwellodemo.domain.Voter;
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
public class VoterController {

    @Autowired
    private VoterService voterService;


    @PostMapping("/createVoter")
    @Operation(summary = "Create a new voter", description = "Creates a new voter and returns the created voter.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Voter successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voter.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<Voter> createVoter(@RequestBody Voter voter){
        log.info("createVoter method called");
        Voter savedVoter = voterService.saveVoter(voter);
        log.info("Voter {} {} saved successfully at {}", savedVoter.getFirstName(),
                savedVoter.getLastName(), LocalDateTime.now());
        return new ResponseEntity<>(savedVoter, HttpStatus.OK);
    }


    @GetMapping("/getAllVoters")
    @Operation(summary = "Get all voters", description = "Retrieves a list of all voters.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Voters retrieved successfully",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Voter.class)))
            ),
            @ApiResponse(responseCode = "404", description = "No voters found"),
    })
    public ResponseEntity<List<Voter>> getAllVotersAsList (){
        log.info("getAllVoters method called");
        List<Voter> allVoters = voterService.getAllVotersAsList();
        log.info("Found {} voters", allVoters.size());
        return new ResponseEntity<>(allVoters, HttpStatus.OK);
    }

    @Operation(summary = "Get a voter by ID", description = "Retrieves a voter by their unique ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Voter retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voter.class))
            ),
            @ApiResponse(responseCode = "404", description = "Voter not found"),
    })
    @GetMapping("/getVoterById/{id}")
    public ResponseEntity<Voter> getVoterById (@PathVariable Long id){
        log.info("getVoterById method called with id {}", id);
        Voter voter = voterService.getVoterById(id);
        return new ResponseEntity<>(voter, HttpStatus.OK);
    }

    @PutMapping("/updateVoter")
    @Operation(summary = "Update voter details", description = "Updates the details of an existing voter.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Voter details updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Voter.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Voter not found"),
    })
    public ResponseEntity<Voter> updateVoterDetails(@RequestBody Voter voter){
        Voter updatedVoter = voterService.updateVoterDetails(voter);
        return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
    }

}













