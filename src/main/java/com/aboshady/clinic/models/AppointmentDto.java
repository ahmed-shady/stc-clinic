package com.aboshady.clinic.models;

import java.time.LocalDate;

public class AppointmentDto {

    private LocalDate date;
    private String patient;
    private boolean cancelled = false;
    private String reason;
}
