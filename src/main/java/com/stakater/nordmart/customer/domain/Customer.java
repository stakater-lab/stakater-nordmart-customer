package com.stakater.nordmart.customer.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table("customers")
@Builder
@Data
public class Customer {

    @PrimaryKey
    private UUID id;

    private String email;
    private String address;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;

}
