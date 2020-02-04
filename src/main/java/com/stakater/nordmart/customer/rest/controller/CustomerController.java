package com.stakater.nordmart.customer.rest.controller;

import com.stakater.nordmart.customer.domain.Customer;
import com.stakater.nordmart.customer.rest.api.CustomerDto;
import com.stakater.nordmart.customer.rest.api.ResponseCodesEnum;
import com.stakater.nordmart.customer.rest.api.RestResponse;
import com.stakater.nordmart.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;

    private CustomerDtoMapper customerDtoMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerDtoMapper customerDtoMapper) {
        this.customerService = customerService;
        this.customerDtoMapper = customerDtoMapper;
    }

    @GetMapping
    public RestResponse<Iterable<Customer>> getAll() {
        List<Customer> customers = customerService.getAll().collect(Collectors.toList());
        return RestResponse.<Iterable<Customer>>of(ResponseCodesEnum.SUCCESS).withData(customers);
    }

    @GetMapping("search")
    public RestResponse<CustomerDto> search(@RequestParam(value = "email") String email) {
        Customer customer = customerService.getByEmail(email);
        return RestResponse.<CustomerDto>of(ResponseCodesEnum.SUCCESS).withData(customerDtoMapper.entityToDto(customer));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RestResponse<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.save(customerDtoMapper.dtoToEntity(customerDto));
        return RestResponse.<CustomerDto>of(ResponseCodesEnum.SUCCESS).withData(customerDtoMapper.entityToDto(customer));
    }

    @PutMapping("/{id}")
    public RestResponse<CustomerDto> update(@RequestBody Customer customer, @PathVariable UUID id) {
        Customer savedCustomer = customerService.update(id, customer);
        return RestResponse.<CustomerDto>of(ResponseCodesEnum.SUCCESS).withData(customerDtoMapper.entityToDto(savedCustomer));
    }

}
