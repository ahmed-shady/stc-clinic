package com.aboshady.clinic.advice;

public class NotFoundException extends RuntimeException {
    String message;

    public NotFoundException(String message){
        super(message);
        this.message = message;
    }
    public NotFoundException(){
    }
}
