package com.backend.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.server.entities.Doctors;
import java.util.List;

@Repository
public interface DoctorsRepo extends JpaRepository<Doctors, String> {

    // Method to find all doctors
    List<Doctors> findAll();

    // Method to save a doctor
    Doctors save(Doctors doctor);
}
