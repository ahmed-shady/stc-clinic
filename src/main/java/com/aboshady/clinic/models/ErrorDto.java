package com.aboshady.clinic.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {
    String message;
    LocalDateTime date;
    public ErrorDto(String message){
        this.date = LocalDateTime.now();
        this.message = message;
    }

}
