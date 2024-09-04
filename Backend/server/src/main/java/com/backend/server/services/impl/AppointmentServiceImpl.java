package com.backend.server.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.backend.server.entities.Appointment;
import com.backend.server.helper.AppointmentStatus;
import com.backend.server.helper.ResourceNotFoundException;
import com.backend.server.repositories.AppointmentRepo;
import com.backend.server.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null || !appointmentRepo.existsById(appointment.getId())) {
            int nextToken = getNextToken(appointment.getDate());
            appointment.setTokenNo(nextToken);
            String appointmentId = UUID.randomUUID().toString();
            appointment.setId(appointmentId);
            appointment.setCreatedAt(LocalDateTime.now());
        }
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
    public int getNextToken(String date) {
        Appointment lastAppointment = appointmentRepo.findTopByDateOrderByTokenNoDesc(date);
        Integer lastTokenNo = (lastAppointment != null) ? lastAppointment.getTokenNo() : null;
        int nextTokenNumber = (lastTokenNo != null) ? lastTokenNo + 1 : 1;
        return nextTokenNumber;
    }

    @Override
    public List<Appointment> findAllByOrderByTokenNoAsc() {
        return appointmentRepo.findAllByOrderByTokenNoAsc();
    }

    @Override
    public Page<Appointment> getAll(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return appointmentRepo.findAll(pageable);
    }

    @Override
    public Page<Appointment> findByPatientId(String patientId, int page, int size) {
        Sort sort = Sort.by("createdAt").ascending(); // Always sort by createdAt in ascending order
        Pageable pageable = PageRequest.of(page, size, sort);
        return appointmentRepo.findByPatientID(patientId, pageable);
    }

    @Override
    public Appointment getById(String id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id " + id));
    }

    @Override
    public Appointment update(Appointment appointment) {
        var OldAppointment = appointmentRepo.findById(appointment.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        OldAppointment.setStatus(appointment.getStatus());
        OldAppointment.setTime(appointment.getTime());
        OldAppointment.setDate(appointment.getDate());

        if (OldAppointment.getStatus() == AppointmentStatus.Scheduled ||
        OldAppointment.getStatus() == AppointmentStatus.Completed) {
            OldAppointment.setIsScheduled("Yes");
        } else {
            OldAppointment.setIsScheduled(null);
        }

        return appointmentRepo.save(OldAppointment);
    }




    @Override
    public Page<Appointment> searchByStatus(AppointmentStatus status, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return appointmentRepo.findByStatus(status, pageable);
    }

    @Override
    public Page<Appointment> searchByPatientId(String patientid, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return appointmentRepo.findByPatientIDContaining(patientid, pageable);
    }

    @Override
    public Page<Appointment> searchByDoctorId(String docid, int size, int page, String sortBy, String order) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return appointmentRepo.findByDoctorIDContaining(docid, pageable);
    }

   


}
