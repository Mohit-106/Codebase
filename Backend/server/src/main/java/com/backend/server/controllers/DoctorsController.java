package com.backend.server.controllers;

import com.backend.server.entities.Doctors;
import com.backend.server.repositories.DoctorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    private DoctorsRepo doctorsRepository;

    // GET request to retrieve all doctors
    @GetMapping
    public ResponseEntity<List<Doctors>> getAllDoctors() {
        List<Doctors> doctorsList = doctorsRepository.findAll();
        if (doctorsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctorsList, HttpStatus.OK);
    }

    // POST request to add a new doctor
    @PostMapping
    public ResponseEntity<Doctors> addDoctor(@RequestBody Doctors doctor) {
        try {
            Doctors savedDoctor = doctorsRepository.save(doctor);
            return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Doctors getDoctorById(@PathVariable String id) {
        return doctorsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));
    }

}
