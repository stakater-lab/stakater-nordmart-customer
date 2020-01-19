package com.stakater.nordmart.customer.service;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import com.stakater.nordmart.customer.CustomerApplication;
import com.stakater.nordmart.customer.config.TestCassandraConfig;
import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = CustomerApplication.class)
@Import(TestCassandraConfig.class)
class CustomerServiceIntegrationTest {

    @Value("${spring.data.cassandra.keyspace-name:placeholder}")
    private String keySpace;

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private Session session;

    @AfterEach
    public void afterEachTest() {
        session.execute("TRUNCATE " + keySpace + ".customers");
    }

    @DisplayName("CreateCustomer")
    @ParameterizedTest
    @MethodSource("com.stakater.nordmart.customer.provider.CustomersProvider#getSimpleCustomerWithoutId")
    public void whenCreate_thenReturnCustomer(Customer entity) {
        Customer saveCustomer = customerService.save(entity);
        Assert.assertNotNull(saveCustomer);
        Assert.assertNotNull(saveCustomer.getId());
    }

    @DisplayName("GetCustomerById")
    @ParameterizedTest
    @MethodSource("com.stakater.nordmart.customer.provider.CustomersProvider#getSimpleCustomerWithoutId")
    public void whenGetById_thenReturnCustomer(Customer entity) {
        Customer savedCustomer = customerService.save(entity);

        Customer customer = customerService.getById(savedCustomer.getId());
        Assert.assertNotNull(customer);
        Assert.assertEquals(savedCustomer.getId(), customer.getId());
        Assert.assertEquals(entity.getEmail(), customer.getEmail());

    }

    @DisplayName("ThrowEntityNotFoundException")
    @Test
    public void whenEntityNull_thenThrowException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> customerService.getById(UUIDs.timeBased()));
    }

    @DisplayName("GetCustomerByEmail")
    @ParameterizedTest
    @MethodSource("com.stakater.nordmart.customer.provider.CustomersProvider#getSimpleCustomerWithoutId")
    public void whenGetByEmail_thenReturnCustomer(Customer entity) {
        Customer savedCustomer = customerService.save(entity);

        Customer customer = customerService.getByEmail(entity.getEmail());
        Assert.assertNotNull(customer);
        Assert.assertEquals(savedCustomer.getId(), customer.getId());
    }

}
