package com.backend.server.entities;

import com.backend.server.helper.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Appointment {
    @Id
    private String id;
    @Column(unique = true)
    private String tokenNo;
    private String doctorID;
    private String patientID;
    private String date;
    private String time;
    private AppointmentStatus status = AppointmentStatus.Pending;
}
