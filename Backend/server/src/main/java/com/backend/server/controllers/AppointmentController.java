package com.backend.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.backend.server.forms.AppointmentForm;
import com.backend.server.helper.AppConstants;
import com.backend.server.services.AppointmentService;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.backend.server.entities.Appointment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody @Valid AppointmentForm appointmentForm,
            BindingResult rBindingResult, Authentication authentication) {

        if (rBindingResult.hasErrors()) {
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

    @GetMapping("/patient/{id}")
    public ResponseEntity<Page<Appointment>> getAppointmentsByPatientId(
            @PathVariable("id") String patientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE+"") int size) {
        Page<Appointment> appointments = appointmentService.findByPatientId(patientId, page, size);
        if (appointments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/view/{id}")
    public String UpdateAppointmentView(@PathVariable("id") String id, Model model) {
        Appointment appointment = appointmentService.getById(id);
        model.addAttribute("appointment", appointment);
        return "user/update_appointment_view";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable("id") String id,
            @ModelAttribute("appointment") Appointment updatedAppointment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("appointment", updatedAppointment);
            return "user/update_appointment_view";
        }

        Appointment existingAppointment = appointmentService.getById(id);
        existingAppointment.setStatus(updatedAppointment.getStatus());
        existingAppointment.setDate(updatedAppointment.getDate());
        existingAppointment.setTime(updatedAppointment.getTime());
        appointmentService.update(existingAppointment);

        return "redirect:/user/appointments";

    }

}
