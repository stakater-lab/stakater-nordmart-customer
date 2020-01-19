package com.stakater.nordmart.customer.rest.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class CustomerDto {

    private UUID id;

    private String email;
    private String address;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;

}
