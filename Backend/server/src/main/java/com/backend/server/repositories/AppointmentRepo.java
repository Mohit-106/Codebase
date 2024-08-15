package com.backend.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.backend.server.entities.Appointment;


public interface AppointmentRepo extends JpaRepository<Appointment,String>{
    // SELECT * FROM Appointment WHERE date = :date ORDER BY tokenNo DESC LIMIT 1
    Appointment findTopByDateOrderByTokenNoDesc(String date);
    List<Appointment> findAllByOrderByTokenNoAsc();
}
