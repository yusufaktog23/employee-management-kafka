package com.aktog.yusuf.employeemanagementlombok.service;


import com.aktog.yusuf.employeemanagementlombok.config.KafkaTopics;
import com.aktog.yusuf.employeemanagementlombok.model.dto.AddressAssignmentDto;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmployeeConsumerService {
    private final Gson gson;
    private final NotificationService notificationService;

    @KafkaListener(
            groupId = KafkaTopics.EMPLOYEE_GROUP,
            topics = KafkaTopics.EMPLOYEE_ADDRESS_ASSIGNMENT
    )
    public void assignAddress(String message){
        log.info("Consumed {}",message);

        notificationService.sendAddressAssignmentNotification(gson.fromJson(message,AddressAssignmentDto.class));

    }

    @KafkaListener(
            groupId = KafkaTopics.EMPLOYEE_GROUP,
            topics = KafkaTopics.EMPLOYEE_ADDRESS_UNASSIGNMENT
    )
    public void removeAddress(String message){
        log.info("Consumed {}",message);

        notificationService.sendAddressRemovedNotification(gson.fromJson(message,AddressAssignmentDto.class));

    }

}
