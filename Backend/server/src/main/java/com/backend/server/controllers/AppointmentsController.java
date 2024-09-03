package com.backend.server.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.server.entities.Appointment;
import com.backend.server.forms.AppointmentForm;
import com.backend.server.helper.AppConstants;
import com.backend.server.helper.Message;
import com.backend.server.helper.MessageType;
import com.backend.server.services.AppointmentService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;




@Controller


@RequestMapping("/user/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping()
    public String viewContacts(@RequestParam(value="page",defaultValue="0") int page,@RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+"") int size,@RequestParam(value="sortBy",defaultValue = "tokenNo") String sortBy, @RequestParam(value="direction",defaultValue = "asc") String direction,Model model, Authentication authentication){
        Page<Appointment> appointmentPage = appointmentService.getAll(page,size,sortBy,direction);
        model.addAttribute("appointmentPage", appointmentPage);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "/user/appointments";
    }

    // Delete contact

    @RequestMapping("/delete/{id}")
    public String deleteAppointmrnt(@PathVariable("id") String id, HttpSession session){
        appointmentService.delete(id);
        return "redirect:/user/appointments";
    }

    

 

}

