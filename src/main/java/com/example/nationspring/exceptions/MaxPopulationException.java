package com.example.nationspring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxPopulationException extends RuntimeException{

    public MaxPopulationException(String msg){

        super(msg);
    }
}
