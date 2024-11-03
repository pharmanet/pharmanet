package com.pharmanet.service.customer;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Customer;
import com.pharmanet.persistence.repositories.ICustomerRepository;
import com.pharmanet.presentation.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements  ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer  customer = modelMapper.map(customerDto, Customer.class);
        Customer customerSaved = customerRepository.save(customer);
        return modelMapper.map(customerSaved, CustomerDto.class);
    }

    @Override
    public String deleteCustomer(Long id) {
        customerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Customer no existe"));
        customerRepository.deleteById(id);
        return "Eliminado con exito";

    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer no existe"));

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setDni(customerDto.getDni());

        Customer customerUpdate = customerRepository.save(customer);
        return modelMapper.map(customerUpdate, CustomerDto.class);
    }
}
