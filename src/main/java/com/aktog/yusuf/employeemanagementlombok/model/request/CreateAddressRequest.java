package com.aktog.yusuf.employeemanagementlombok.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {
    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private String zipCode;
}
