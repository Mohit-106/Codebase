package com.backend.server.forms;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AppointmentForm {

    @NotBlank(message = "Patient Name is required")
    private String patientID;

    @NotBlank(message = "Doctor is required")
    private String doctorID;

    private String date;

    private String time;

}

