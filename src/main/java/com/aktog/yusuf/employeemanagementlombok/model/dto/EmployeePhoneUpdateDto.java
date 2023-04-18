package com.aktog.yusuf.employeemanagementlombok.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePhoneUpdateDto {
    private String employeeId;
    private String oldPhoneNumber;
    private String newPhoneNumber;
}
