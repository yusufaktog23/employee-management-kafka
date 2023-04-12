package com.aktog.yusuf.employeemanagementlombok.controller;


import com.aktog.yusuf.employeemanagementlombok.model.dto.AddressDto;
import com.aktog.yusuf.employeemanagementlombok.model.request.CreateAddressRequest;
import com.aktog.yusuf.employeemanagementlombok.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest request) {
        return ResponseEntity.ok(addressService.createAddress(request));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAddresses());
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody CreateAddressRequest request,
                                                    @PathVariable String addressId) {
        return ResponseEntity.ok(addressService.updateAddress(request, addressId));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable String addressId) {
        return ResponseEntity.ok(addressService.deleteAddress(addressId));
    }
}
