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
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Appointment {
    @Id
    private String id;
    @Column(unique = true)
    private int tokenNo;
    private String doctorID;
    private String patientID;
    private String date;
    private String time;
    @Enumerated(EnumType.ORDINAL)
    private AppointmentStatus status = AppointmentStatus.Pending;
    private String isScheduled;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    private User user;

    public void setIsScheduled(String isScheduled) {
        this.isScheduled = isScheduled;
    }
    
}
