package com.stakater.nordmart.customer.repository;

import com.stakater.nordmart.customer.domain.Customer;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends CassandraRepository<Customer, UUID> {

    @AllowFiltering
    Optional<Customer> findByEmail(String email);

}
