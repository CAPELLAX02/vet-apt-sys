package com.dbms.assignment.controller;

import com.dbms.assignment.model.Appointment;
import com.dbms.assignment.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestParam Long petId,
                                                         @RequestParam Long vetId,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return ResponseEntity.ok(appointmentService.createAppointment(petId, vetId, time));
    }

    @GetMapping("/vet/{vetId}")
    public ResponseEntity<Set<Appointment>> getAppointmentsForVet(@PathVariable Long vetId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForVet(vetId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointmentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> completeAppointment(@PathVariable Long id) {
        appointmentService.completeAppointmentById(id);
        return ResponseEntity.noContent().build();
    }

}
