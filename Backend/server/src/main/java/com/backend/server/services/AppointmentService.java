package com.backend.server.services;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.backend.server.entities.Appointment;


public interface AppointmentService {

    //save 
    Appointment save (Appointment appointment);

    // Get Appointments
    List<Appointment> getAll();

    //delete 
    void delete(String id);

    // Getting token
    int getNextToken(String date);

    List<Appointment> findAllByOrderByTokenNoAsc();

    Page<Appointment> getAll(int page, int size, String sortBy, String direction);

    Page<Appointment> findByPatientId(String patientId, int page, int size);
    Appointment getById(String id);

    Appointment update(Appointment appointment);

}
