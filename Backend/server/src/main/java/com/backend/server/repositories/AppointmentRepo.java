package com.backend.server.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.server.entities.Appointment;
import com.backend.server.entities.User;
import com.backend.server.helper.AppointmentStatus;


public interface AppointmentRepo extends JpaRepository<Appointment,String>{
    List<Appointment> findByUser(User user);

    // SELECT * FROM Appointment WHERE date = :date ORDER BY tokenNo DESC LIMIT 1
    Appointment findTopByDateOrderByTokenNoDesc(String date);
    List<Appointment> findAllByOrderByTokenNoAsc();
    Page<Appointment> findAll(Pageable pageable);
    Page<Appointment> findByPatientID(String patientId, Pageable pageable);

    @Query("SELECT a FROM Appointment a WHERE a.user.id = :userId")
    List<Appointment> findByUserId(@Param("userId") String userId);

    Page<Appointment> findByStatus(AppointmentStatus statuskeyword, Pageable pageable);
    Page<Appointment> findByDoctorIDContaining(String did, Pageable pageable);
    Page<Appointment> findByPatientIDContaining(String pid, Pageable pageable);
    
}
