package com.pharmanet.service.customer;

import com.pharmanet.presentation.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);
    String deleteCustomer(Long id);
    List<CustomerDto> findAll();
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
}
