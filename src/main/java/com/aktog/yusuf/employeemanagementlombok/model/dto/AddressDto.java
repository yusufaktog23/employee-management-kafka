package com.aktog.yusuf.employeemanagementlombok.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private String id = "";
    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private String zipCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String employeeId;

}
