package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.ProviderDto;
import com.pharmanet.service.provider.IProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/providers")
@Tag(name = "Proveedores", description = "Crud proveedores")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @Operation(
            summary = "Registrar proveedor",
            description = "Registrar proveedor para saber el tipo de proveedor de un producto"
    )
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProviderDto> addProvider(@RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.addProvider(providerDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar proveedor",
            description = "Eliminar proveedor por id de lo contrario arroja un error"
    )
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteProvider(@PathVariable Long id){
        return new ResponseEntity<>(providerService.deleteProvider(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Listar proveedores",
            description = "Listar proveedores"
    )
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ProviderDto>> findAll(){
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar un proveedor",
            description = "Actualizar un proveedor por id de lo contrario arroja un error"
    )
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable Long id, @RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.updateProvider(id, providerDto), HttpStatus.OK);
    }
}
