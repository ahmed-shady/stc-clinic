package com.aboshady.clinic.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="DATE")
    //@Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "PATIENT", nullable = false)
    private String patient;

    @Column(name = "CANCELLED")
    boolean cancelled = false;

    @Column(name = "REASON")
    private String reason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id) && date.equals(that.date) && patient.equals(that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, patient);
    }
}
