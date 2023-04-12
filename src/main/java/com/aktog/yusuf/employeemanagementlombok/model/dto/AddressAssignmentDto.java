package com.aktog.yusuf.employeemanagementlombok.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressAssignmentDto {
    String addressId;
    String employeeId;
}
