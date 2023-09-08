package com.zaccone.onwellodemo.exceptions;

import com.zaccone.onwellodemo.exceptions.exceptions.candidateExceptions.CandidateAlreadyRegisteredException;
import com.zaccone.onwellodemo.exceptions.exceptions.candidateExceptions.CandidateNotFoundException;
import com.zaccone.onwellodemo.exceptions.exceptions.voteExceptions.VoteNotFoundException;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterAlreadyRegisteredException;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterNotFoundException;
import com.zaccone.onwellodemo.exceptions.exceptions.voterExceptions.VoterVotedAlready;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class OnwelloControllerAdvice {

    @ExceptionHandler(VoterAlreadyRegisteredException.class)
    public ResponseEntity<OnwelloError> handleVoterAlreadyRegisteredException(VoterAlreadyRegisteredException e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }


    @ExceptionHandler(VoterNotFoundException.class)
    public ResponseEntity<OnwelloError> handleVoterNotFoundException(VoterNotFoundException e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }

    @ExceptionHandler(VoterVotedAlready.class)
    public ResponseEntity<OnwelloError> handleVoterNotFoundException(VoterVotedAlready e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }


    @ExceptionHandler(CandidateAlreadyRegisteredException.class)
    public ResponseEntity<OnwelloError> handleCandidateAlreadyRegisteredException(CandidateAlreadyRegisteredException e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }


    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<OnwelloError> handleCandidateNotFoundException(CandidateNotFoundException e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }

    @ExceptionHandler(VoteNotFoundException.class)
    public ResponseEntity<OnwelloError> handleVoteNotFoundException(VoteNotFoundException e) {

        OnwelloError error = OnwelloError.builder()
                .httpsCode(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }

}
