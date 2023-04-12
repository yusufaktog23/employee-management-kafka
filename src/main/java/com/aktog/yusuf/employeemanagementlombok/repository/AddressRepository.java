package com.aktog.yusuf.employeemanagementlombok.repository;

import com.aktog.yusuf.employeemanagementlombok.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

}
