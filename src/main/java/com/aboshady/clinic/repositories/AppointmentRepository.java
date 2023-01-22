package com.aboshady.clinic.repositories;

import com.aboshady.clinic.models.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByPatient(String patient);
    List<Appointment> findByPatientAndDateLessThan(String patient, LocalDate date);


}
