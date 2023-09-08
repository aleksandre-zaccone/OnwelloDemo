package com.zaccone.onwellodemo.exceptions.exceptions.candidateExceptions;

public class CandidateAlreadyRegisteredException extends RuntimeException{

    public CandidateAlreadyRegisteredException(String message){
        super(message);
    }

}
