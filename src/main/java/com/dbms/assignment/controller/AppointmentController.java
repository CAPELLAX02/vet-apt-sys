package com.dbms.assignment.controller;

import com.dbms.assignment.dto.AppointmentResponse;
import com.dbms.assignment.dto.CreateAppointmentRequest;
import com.dbms.assignment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody CreateAppointmentRequest req) {
        return ResponseEntity.ok(appointmentService.createAppointment(req));
    }

    @GetMapping("/vet/{vetId}")
    public ResponseEntity<Set<AppointmentResponse>> getForVet(@PathVariable Long vetId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForVet(vetId));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancel(@PathVariable Long id) {
        appointmentService.cancelAppointmentById(id);
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<AppointmentResponse> complete(@PathVariable Long id) {
        appointmentService.completeAppointmentById(id);
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

}
