package com.zaccone.onwellodemo.exceptions.exceptions.voteExceptions;

public class VoteNotFoundException extends  RuntimeException{

    public VoteNotFoundException(String message){
        super(message);
    }
}
