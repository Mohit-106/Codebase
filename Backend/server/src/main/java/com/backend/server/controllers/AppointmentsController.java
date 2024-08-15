package com.backend.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.server.entities.Appointment;
import com.backend.server.services.AppointmentService;



@Controller


@RequestMapping("/user/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping()
    public String viewContacts(Model model, Authentication authentication){
        List<Appointment> appointments = appointmentService.findAllByOrderByTokenNoAsc();
        model.addAttribute("appoitments", appointments);
        return "/user/appointments";
    }
}

