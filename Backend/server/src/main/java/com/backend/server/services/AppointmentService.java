package com.backend.server.services;
import java.util.List;

import org.springframework.data.domain.Page;

import com.backend.server.entities.Appointment;
import com.backend.server.helper.AppointmentStatus;


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

    Page<Appointment> searchByStatus(AppointmentStatus status, int size, int page, String sortBy, String order);
    Page<Appointment> searchByPatientId(String patientid, int size, int page, String sortBy, String order);
    Page<Appointment> searchByDoctorId(String docid, int size, int page, String sortBy, String order);


}
