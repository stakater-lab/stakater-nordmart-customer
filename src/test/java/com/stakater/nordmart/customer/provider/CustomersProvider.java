package com.stakater.nordmart.customer.provider;

import com.datastax.driver.core.utils.UUIDs;
import com.stakater.nordmart.customer.domain.Customer;

import java.util.stream.Stream;

public class CustomersProvider {

    public static Customer getSavedCustomer() {
        return Customer.builder().email("customer123@gmail.com")
                .gender("F")
                .address("34 charle avenue")
                .id(UUIDs.timeBased())
                .build();
    }

    public static Stream<Customer> getSimpleCustomerWithoutId() {
        Customer customer = Customer.builder().email("customer123@gmail.com")
                .gender("F")
                .address("34 charle avenue")
                .build();
        return Stream.of(customer);
    }

}
