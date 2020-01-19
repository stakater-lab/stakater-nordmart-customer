package com.stakater.nordmart.customer.rest.controller;

import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.rest.api.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

    CustomerDto entityToDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerDto.builder()
                .address(customer.getAddress())
                .dateOfBirth(customer.getDateOfBirth())
                .email(customer.getEmail())
                .gender(customer.getGender())
                .phoneNumber(customer.getPhoneNumber())
                .id(customer.getId())
                .build();
    }

    Customer dtoToEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        return Customer.builder()
                .address(customerDto.getAddress())
                .dateOfBirth(customerDto.getDateOfBirth())
                .email(customerDto.getEmail())
                .gender(customerDto.getGender())
                .phoneNumber(customerDto.getPhoneNumber())
                .id(customerDto.getId())
                .build();
    }

}
