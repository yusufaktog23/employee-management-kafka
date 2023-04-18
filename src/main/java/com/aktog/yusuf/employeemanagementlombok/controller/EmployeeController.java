package com.aktog.yusuf.employeemanagementlombok.controller;


import com.aktog.yusuf.employeemanagementlombok.model.dto.EmployeeDto;
import com.aktog.yusuf.employeemanagementlombok.model.request.CreateEmployeeRequest;
import com.aktog.yusuf.employeemanagementlombok.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listEmployees() {
        return ResponseEntity.ok(employeeService.getEmployeeList());
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody CreateEmployeeRequest request,
                                                      @PathVariable String employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployee(request, employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }

    @PutMapping("/assign/{employeeId}/{addressId}")
    public ResponseEntity<EmployeeDto> assignAddress(@PathVariable String employeeId,
                                                     @PathVariable String addressId) {
        return new ResponseEntity<>(employeeService.assignAddress(employeeId, addressId), HttpStatus.OK);
    }

    @PutMapping("/remove/{employeeId}/{addressId}")
    public ResponseEntity<EmployeeDto> removeAddress(@PathVariable String employeeId,
                                                     @PathVariable String addressId) {
        return new ResponseEntity<>(employeeService.removeAddress(employeeId, addressId), HttpStatus.OK);
    }

    @PutMapping("/phone/{employeeId}/{phoneNumber}")
    public ResponseEntity<EmployeeDto> updatePhoneNumber(@PathVariable String employeeId,
                                                         @PathVariable String phoneNumber) {
        return new ResponseEntity<>(employeeService.updatePhoneNumber(employeeId, phoneNumber), HttpStatus.OK);
    }

}