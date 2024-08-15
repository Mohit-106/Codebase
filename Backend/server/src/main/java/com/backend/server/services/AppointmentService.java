package com.backend.server.services;

import java.util.List;


import com.backend.server.entities.Appointment;


public interface AppointmentService {

    //save 
    Appointment save (Appointment appointment);

    // Get Appointments
    List<Appointment> getAll();

    //delete 
    void delete(String id);

    // Getting token
    String getNextToken(String date);

    List<Appointment> findAllByOrderByTokenNoAsc();

}
