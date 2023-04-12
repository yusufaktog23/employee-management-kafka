package com.aktog.yusuf.employeemanagementlombok.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private String id = "";
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private List<String> addressIds;

}
