package com.stakater.nordmart.customer.service;

import com.datastax.driver.core.utils.UUIDs;
import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.provider.CustomersProvider;
import com.stakater.nordmart.customer.repository.CustomerRepository;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceUnitTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @DisplayName("CreateCustomer")
    @ParameterizedTest
    @MethodSource("com.stakater.nordmart.customer.provider.CustomersProvider#getSimpleCustomerWithoutId")
    public void whenCreate_thenReturnCustomer(Customer newEntity) {
        Customer customerSaved = CustomersProvider.getSavedCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customerSaved);

        Customer customer = customerService.save(newEntity);
        verify(customerRepository, times(1)).save(any(Customer.class));

        Assertions.assertEquals(customerSaved.getId(), customer.getId());
        Assertions.assertEquals(customerSaved.getEmail(), customer.getEmail());
    }

    @DisplayName("GetCustomerById")
    @Test
    public void whenGetById_thenReturnCustomer() {
        Customer savedCustomer = CustomersProvider.getSavedCustomer();
        when(customerRepository.findById(any()))
                .thenReturn(Optional.of(savedCustomer));

        customerService.getById(savedCustomer.getId());
        verify(customerRepository, times(1)).findById(ArgumentMatchers.eq(savedCustomer.getId()));
    }

    @DisplayName("ThrowEntityNotFoundException")
    @Test
    public void whenEntityNull_thenThrowException() {
        when(customerRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> customerService.getById(UUIDs.timeBased()));
    }


}
