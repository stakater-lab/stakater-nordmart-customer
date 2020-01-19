package com.stakater.nordmart.customer.service;

import com.datastax.driver.core.utils.UUIDs;
import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.repository.CustomerRepository;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Stream<Customer> getAll() {
        return customerRepository.findAll().stream();
    }

    @Override
    public Customer getById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new EntityNotFoundException("The customer with id " + id + " doesn't exist");
        }
        return customer.get();
    }

    @Override
    public Customer getByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setId(UUIDs.timeBased());
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(UUID id, Customer customer) {
        Customer customerToUpdate = getById(id);
        customer.setId(customerToUpdate.getId());
        return customerRepository.save(customer);
    }

}
