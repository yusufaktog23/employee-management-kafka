package com.aktog.yusuf.employeemanagementlombok.service;

import com.aktog.yusuf.employeemanagementlombok.model.Address;
import com.aktog.yusuf.employeemanagementlombok.model.dto.AddressDto;
import com.aktog.yusuf.employeemanagementlombok.model.request.CreateAddressRequest;
import com.aktog.yusuf.employeemanagementlombok.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressDto createAddress(CreateAddressRequest createAddressRequest) {

        Address address = new Address(
                "",
                createAddressRequest.getCountry(),
                createAddressRequest.getCity(),
                createAddressRequest.getStreet(),
                createAddressRequest.getBuildingNumber(),
                createAddressRequest.getZipCode(),
                null
        );

        return mapToDto(addressRepository.save(address));
    }

    public Address findById(String id) {

        return addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Address not found : " + id));
    }

    public String deleteAddress(String id) {
        findById(id);
        addressRepository.deleteById(id);
        return "Address id : " + " has been deleted";
    }


    private AddressDto mapToDto(Address from) {

        return new AddressDto(
                from.getId(),
                from.getCountry(),
                from.getCity(),
                from.getStreet(),
                from.getBuildingNumber(),
                from.getZipCode(),
                Objects.nonNull(from.getEmployee()) ? from.getEmployee().getId() : ""
        );
    }

    public List<AddressDto> getAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public AddressDto updateAddress(CreateAddressRequest request, String addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new EntityNotFoundException("Address not found : " + addressId));

        Address toUpdate = new Address(
                addressId,
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getBuildingNumber(),
                request.getZipCode(),
                address.getEmployee()
        );

        return mapToDto(addressRepository.save(toUpdate));

    }


}
