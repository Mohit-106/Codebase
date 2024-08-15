package com.backend.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.server.forms.AppointmentForm;
import com.backend.server.services.AppointmentService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.backend.server.entities.Appointment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;


@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // REST API for Android
    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody @Valid AppointmentForm appointmentForm, BindingResult rBindingResult){

        if(rBindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation errors occured");
        }

        Appointment appointment = new Appointment();
        appointment.setPatientID(appointmentForm.getPatientID());
        appointment.setDoctorID(appointmentForm.getDoctorID());
        appointment.setDate(appointmentForm.getDate());
        appointment.setTime(appointmentForm.getTime());
        Appointment savedAppointment = appointmentService.save(appointment);
        return ResponseEntity.ok(savedAppointment);

    }

}
