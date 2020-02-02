package com.stakater.nordmart.customer.service;

import com.datastax.driver.core.utils.UUIDs;
import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.repository.CustomerRepository;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Stream<Customer> getAll() {
        LOG.debug("Get all customers");
        return customerRepository.findAll().stream();
    }

    @Override
    public Customer getById(UUID id) {
        LOG.debug("Get customer By id: {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new EntityNotFoundException("The customer with id " + id + " doesn't exist");
        }

        LOG.debug("Successfully return customer with ID {}", id);
        return customer.get();
    }

    @Override
    public Customer getByEmail(String email) {
        LOG.debug("Get customer by email: {}", email);
        Customer customer = customerRepository.findByEmail(email).orElse(null);

        LOG.debug("Successfully return customer");
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        LOG.debug("Save new customer profile");
        customer.setId(UUIDs.timeBased());
        Customer savedCustomer = customerRepository.save(customer);

        LOG.debug("New customer profile with id {} saved", savedCustomer.getId());
        return savedCustomer;
    }

    @Override
    public Customer update(UUID id, Customer customer) {
        LOG.debug("Update customer profile with id: {}", id);
        Customer customerToUpdate = getById(id);
        customer.setId(customerToUpdate.getId());
        Customer updatedCustomer = customerRepository.save(customer);

        LOG.debug("Customer profile with id {} updated", id);
        return updatedCustomer;
    }

}
