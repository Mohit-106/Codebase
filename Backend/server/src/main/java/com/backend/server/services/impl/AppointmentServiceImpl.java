package com.backend.server.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.server.entities.Appointment;
import com.backend.server.helper.ResourceNotFoundException;
import com.backend.server.repositories.AppointmentRepo;
import com.backend.server.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public Appointment save(Appointment appointment) {
        String nextToken = getNextToken(appointment.getDate());
        appointment.setTokenNo(nextToken);
        String appointmentid = UUID.randomUUID().toString();
        appointment.setId(appointmentid);
        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public void delete(String id) {
        var appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appointmentRepo.delete(appointment);
    }

    @Override
    public String getNextToken(String date) {
        Appointment lastAppointment = appointmentRepo.findTopByDateOrderByTokenNoDesc(date);

        // Check if lastAppointment is not null and get the token number
        String lastTokenNo = (lastAppointment != null && lastAppointment.getTokenNo() != null)
                ? lastAppointment.getTokenNo()
                : "0";

        int nextTokenNumber = Integer.parseInt(lastTokenNo) + 1;
        return String.valueOf(nextTokenNumber);
    }

    @Override
    public List<Appointment> findAllByOrderByTokenNoAsc() {
        return appointmentRepo.findAllByOrderByTokenNoAsc();
    }

}
