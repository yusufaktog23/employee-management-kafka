package com.aktog.yusuf.employeemanagementlombok.service;

import com.aktog.yusuf.employeemanagementlombok.config.KafkaTopics;
import com.aktog.yusuf.employeemanagementlombok.model.Address;
import com.aktog.yusuf.employeemanagementlombok.model.Employee;
import com.aktog.yusuf.employeemanagementlombok.model.dto.AddressAssignmentDto;
import com.aktog.yusuf.employeemanagementlombok.model.dto.EmployeeDto;
import com.aktog.yusuf.employeemanagementlombok.model.request.CreateEmployeeRequest;
import com.aktog.yusuf.employeemanagementlombok.repository.EmployeeRepository;
import com.aktog.yusuf.employeemanagementlombok.service.kafka.KafkaProducerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final KafkaProducerService kafkaProducerService;


    //
    public EmployeeDto createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        Employee employee = new Employee("",
                createEmployeeRequest.getName(),
                createEmployeeRequest.getSurname(),
                createEmployeeRequest.getEmail(),
                createEmployeeRequest.getBirthDate(),
                createEmployeeRequest.getSalary(),
                Collections.emptySet()
        );

        Employee saved = employeeRepository.save(employee);

        return mapToDto(saved);
    }

    public EmployeeDto mapToDto(Employee from) {
        return EmployeeDto.builder()
                .id(from.getId())
                .name(from.getName())
                .surname(from.getSurname())
                .age(LocalDate.now().getYear() - from.getBirthDate().getYear())
                .email(from.getEmail())
                .addressIds(from.getAddresses().stream().map(Address::getId).toList())
                .build();
    }

    public List<EmployeeDto> getEmployeeList() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Employee findByEmployeeId(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new EntityNotFoundException("Entity Not Found : " + employeeId));
    }

    public EmployeeDto updateEmployee(CreateEmployeeRequest request, String employeeId) {
        Employee employee = findByEmployeeId(employeeId);

        Employee toUpdate = new Employee(
                employeeId,
                request.getName(),
                request.getSurname(),
                request.getEmail(),
                employee.getBirthDate(),
                request.getSalary(),
                employee.getAddresses()
        );

        return mapToDto(employeeRepository.save(toUpdate));

    }

    public String deleteEmployee(String employeeId) {

        findByEmployeeId(employeeId);

        return "Employee id : " + employeeId + " has been deleted";
    }

    public EmployeeDto assignAddress(String employeeId, String addressId) {
        Employee employee = findByEmployeeId(employeeId);

        Set<Address> addresses = employee.getAddresses();
        addresses.add(addressService.findById(addressId));

        addresses.forEach(address -> address.setEmployee(employee));

        Employee toUpdate = new Employee(
                employeeId,
                employee.getName(),
                employee.getSurname(),
                employee.getEmail(),
                employee.getBirthDate(),
                employee.getSalary(),
                addresses);

        kafkaProducerService.produce(KafkaTopics.EMPLOYEE_ADDRESS_ASSIGNMENT, new AddressAssignmentDto(employeeId, addressId));

        return mapToDto(employeeRepository.save(toUpdate));
    }

    public EmployeeDto removeAddress(String employeeId, String addressId) {
        Employee employee = findByEmployeeId(employeeId);
        Address address = addressService.findById(addressId);

        Set<Address> addresses = employee.getAddresses();
        addresses.remove(address);

        Employee toUpdate = new Employee(
                employeeId,
                employee.getName(),
                employee.getSurname(),
                employee.getEmail(),
                employee.getBirthDate(),
                employee.getSalary(),
                addresses);

        kafkaProducerService.produce(KafkaTopics.EMPLOYEE_ADDRESS_UNASSIGNMENT, new AddressAssignmentDto(addressId, employeeId));

        return mapToDto(employeeRepository.save(toUpdate));
    }
}


