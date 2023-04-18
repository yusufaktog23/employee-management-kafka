package com.aktog.yusuf.employeemanagementlombok.service;

import com.aktog.yusuf.employeemanagementlombok.model.dto.AddressAssignmentDto;
import com.aktog.yusuf.employeemanagementlombok.model.dto.EmployeePhoneUpdateDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class NotificationService {

    public void sendAddressAssignmentNotification(AddressAssignmentDto dto) {
        log.info("Address id {} is assigned to Employee {}", dto.getAddressId(), dto.getEmployeeId());
    }

    public void sendAddressRemovedNotification(AddressAssignmentDto dto) {
        log.info("Address id {} is removed from Employee {}", dto.getAddressId(), dto.getEmployeeId());
    }

    public void sendPhoneNumberUpdatedNotification(EmployeePhoneUpdateDto dto) {
        log.info("Employee id {} has changed phone number from {} to {} ",
                dto.getEmployeeId(),
                dto.getOldPhoneNumber(),
                dto.getNewPhoneNumber());
    }
}
