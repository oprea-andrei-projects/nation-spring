package com.example.nationspring.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class SameNationException extends RuntimeException{

    public SameNationException(String msg){

        super(msg);
    }

}
