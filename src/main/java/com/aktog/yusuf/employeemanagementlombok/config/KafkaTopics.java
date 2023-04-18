package com.aktog.yusuf.employeemanagementlombok.config;

public class KafkaTopics {
    public static final String EMPLOYEE_ADDRESS_ASSIGNMENT = "employee-address-assignment";
    public static final String EMPLOYEE_ADDRESS_UNASSIGNMENT = "employee-address-unassignment";
    public static final String EMPLOYEE_GROUP = "employee-group";
    public static final String EMPLOYEE_PHONE = "employee-phone";

    private KafkaTopics() {
    }
}