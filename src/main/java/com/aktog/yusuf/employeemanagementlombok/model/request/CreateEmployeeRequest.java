package com.aktog.yusuf.employeemanagementlombok.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @Size(min = 10, max = 10, message = "phone number must be 10 digits")
    private String phoneNumber;

    private LocalDate birthDate;

}
