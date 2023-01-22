package com.aboshady.clinic.services;

import com.aboshady.clinic.advice.NotFoundException;
import com.aboshady.clinic.models.Appointment;
import com.aboshady.clinic.models.CancelRequest;
import com.aboshady.clinic.repositories.AppointmentRepository;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllToday(){
        return appointmentRepository.findByDate(LocalDate.now());
    }
    public List<Appointment> getByDate(LocalDate date){
        return appointmentRepository.findByDate(date);
    }
    public List<Appointment> getByPatient(String patient){
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getHistoryAppointmentsOfPatient(String patient){
        return appointmentRepository.findByPatientAndDateLessThan(patient, LocalDate.now());
    }

    public Appointment cancelAppointment(Long id, CancelRequest cancelRequest){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Appointment of id, %s is not found", id)));
        appointment.setCancelled(true);
        appointment.setReason(cancelRequest.getReason());
        return appointmentRepository.save(appointment);
    }
}
