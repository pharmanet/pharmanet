package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CustomerDto;
import com.pharmanet.service.customer.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Clientes", description = "APIs para la gestión de clientes")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new customer", description = "Permite a un administrador agregar un nuevo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)
    })
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto){
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update a customer", description = "Permite a un administrador actualizar a un cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDto customerDto){
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDto), HttpStatus.OK);
    }


    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a customer", description = "Permite un administrador eliminar un cliente por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteCustomer(id),HttpStatus.OK);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','CAJERO')")
    @Operation(summary = "Get all customers", description = "Permite que un Administrador o CAJERO recupere a todos los clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
    })
    public ResponseEntity<List<CustomerDto>> findCustomer(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
}
