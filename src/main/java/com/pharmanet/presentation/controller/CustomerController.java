package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CustomerDto;
import com.pharmanet.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDto), HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findCustomer(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
}
