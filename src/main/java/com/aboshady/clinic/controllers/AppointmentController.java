package com.aboshady.clinic.controllers;


import com.aboshady.clinic.models.Appointment;
import com.aboshady.clinic.models.CancelRequest;
import com.aboshady.clinic.services.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @PostMapping
    @Tag(name = "Add new Appointment", description = "Post an appointment body to add it")
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment){
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @GetMapping
    @Tag(name = "get today's appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsToday(){
        List<Appointment> appointments = appointmentService.getAllToday();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/date/{date}")
    @Tag(name = "get appointments by date", description = "specify date in the path variable")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        List<Appointment> appointments = appointmentService.getByDate(date);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("patient/{patient}")
    @Tag(name = "get appointments by patient", description = "specify patient name in path")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable String patient){
        List<Appointment> appointments = appointmentService.getByPatient(patient);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("history/patient/{patient}")
    @Tag(name = "get history (past) appointments for a patient", description = "specify patient name in path")
    public ResponseEntity<List<Appointment>> getHistoryAppointments(@PathVariable String patient){
        List<Appointment> appointments = appointmentService.getHistoryAppointmentsOfPatient(patient);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("cancel/{id}")
    @Tag(name = "Cancel an appointment", description = "add the description to the body")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id, @RequestBody CancelRequest cancelRequest){
        return ResponseEntity.ok(appointmentService.cancelAppointment(id, cancelRequest));
    }

}
