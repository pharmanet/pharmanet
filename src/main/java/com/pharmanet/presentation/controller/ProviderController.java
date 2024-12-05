package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CustomerDto;
import com.pharmanet.presentation.dto.ProviderDto;
import com.pharmanet.service.provider.IProviderService;
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
@RequestMapping("/api/v1/providers")
@Tag(name = "Proveedores", description = "APIS gestion de proveedores")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new proveedor", description = "Permite a un administrador agregar un nuevo proveedor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<ProviderDto> addProvider(@RequestBody @Valid ProviderDto providerDto){
        return new ResponseEntity<>(providerService.addProvider(providerDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a provider", description = "Permite un administrador eliminar un proveedor por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deleteProvider(@PathVariable Long id){
        return new ResponseEntity<>(providerService.deleteProvider(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get all providers", description = "Permite que un Administrador recupere a todos los proveedores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
    })
    public ResponseEntity<List<ProviderDto>> findAll(){
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update a provider", description = "Permite a un administrador actualizar a un proveedor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully",
                    content = @Content(schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable Long id, @RequestBody @Valid ProviderDto providerDto){
        return new ResponseEntity<>(providerService.updateProvider(id, providerDto), HttpStatus.OK);
    }
}
