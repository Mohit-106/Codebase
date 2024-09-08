package com.backend.server.controllers;
import java.util.List;

import org.apache.logging.log4j.Logger;
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
import com.backend.server.helper.AppConstants;
import com.backend.server.helper.AppointmentStatus;
import com.backend.server.services.AppointmentService;

import jakarta.servlet.http.HttpSession;





@Controller
@RequestMapping("/user/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentService appointmentService;    

    @RequestMapping()
    public String viewAppointments(@RequestParam(value="page",defaultValue="0") int page,@RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+"") int size,@RequestParam(value="sortBy",defaultValue = "tokenNo") String sortBy, @RequestParam(value="direction",defaultValue = "asc") String direction,Model model, Authentication authentication){
        Page<Appointment> appointmentPage = appointmentService.getAll(page,size,sortBy,direction);
        model.addAttribute("appointmentPage", appointmentPage);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        return "/user/appointments";
    }

    // Delete appointment

    @RequestMapping("/delete/{id}")
    public String deleteAppointmrnt(@PathVariable("id") String id, HttpSession session){
        appointmentService.delete(id);
        return "redirect:/user/appointments";
    }

    // Search Appointment
    @GetMapping("/search")
    public String searchHandler(@RequestParam("field") String field,@RequestParam(value="page", defaultValue = AppConstants.PAGE_SIZE+"") int size ,@RequestParam("keyword") String value, @RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="sortBy",defaultValue = "tokenNo") String sortBy, @RequestParam(value="direction",defaultValue = "asc") String direction, Model model){

        Page<Appointment> appointmentPage = null;
        if(field.equalsIgnoreCase("Status")){
            AppointmentStatus status = AppointmentStatus.Delayed;
            if(value.equalsIgnoreCase("pending")){
                status = AppointmentStatus.Pending;
            }else if(value.equalsIgnoreCase("completed")){
                status = AppointmentStatus.Completed;
            }else if(value.equalsIgnoreCase("scheduled")){
                status = AppointmentStatus.Scheduled;
            }else if(value.equalsIgnoreCase("rescheduled")){
                status = AppointmentStatus.Rescheduled;
            }else if(value.equalsIgnoreCase("rejected")){
                status = AppointmentStatus.Rejected;
            }else if(value.equalsIgnoreCase("cancelled")){
                status = AppointmentStatus.Cancelled;
            }
            appointmentPage = appointmentService.searchByStatus(status,size,page,sortBy,direction);
        }else if(field.equalsIgnoreCase("Patient")){
            appointmentPage = appointmentService.searchByPatientId(value,size,page,sortBy,direction);
        }else{
            appointmentPage = appointmentService.searchByDoctorId(value,size,page,sortBy,direction);
        }

        model.addAttribute("appointmentPage", appointmentPage);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/search";
    }

    

 

}

