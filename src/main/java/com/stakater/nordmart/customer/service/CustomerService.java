package com.stakater.nordmart.customer.service;

import com.stakater.nordmart.customer.domain.Customer;

import java.util.UUID;
import java.util.stream.Stream;

public interface CustomerService {

    Stream<Customer> getAll();

    Customer getById(UUID id);

    Customer getByEmail(String email);

    Customer save(Customer Customer);

    Customer update(UUID id, Customer customer);

}
