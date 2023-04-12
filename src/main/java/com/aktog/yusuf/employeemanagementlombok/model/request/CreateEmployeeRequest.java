package com.aktog.yusuf.employeemanagementlombok.model.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    private String name;
    private String surname;
    @Email(message = "Not valid email format")
    private String email;
    private Integer salary;

    private LocalDate birthDate;

}
