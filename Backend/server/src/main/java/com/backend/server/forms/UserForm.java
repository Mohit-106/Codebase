package com.backend.server.forms;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class UserForm {
    @Size(min = 3, message = "Min 3 Characters is required")
    private String name;
    @Size(min = 12, message = "Min 12 Characters is required")
    private String govtID;
    @Size(min = 6, message = "Min 12 Characters is required")
    private String password;
    @Size(min = 10, message = "Min 12 Characters is required")
    private String phoneNumber;
}

