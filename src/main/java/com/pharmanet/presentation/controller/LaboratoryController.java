package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.LaboratoryDto;
import com.pharmanet.service.laboratory.ILaboratoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratories")
@Tag(name = "Laboratorios", description = "Laboratorios para asignar a los medicamentos")
public class LaboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;

    @Operation(
            summary = "Registrar laboratorio",
            description = "Registrar laboratorio para saber el laboratorio de un medicamento"
    )
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<LaboratoryDto> addLaboratory(@RequestBody @Valid LaboratoryDto laboratoryDto) {
        return new ResponseEntity<>(laboratoryService.addLaboratory(laboratoryDto), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Eliminar laboratorio",
            description = "Eliminar laboratorio por id"
    )
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteLaboratory(@PathVariable Long id){
        return new ResponseEntity<>(this.laboratoryService.deleteLaboratory(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Listar los laboratorios",
            description = "Listar todos los laboratorios"
    )
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<LaboratoryDto>> findAll(){
        return new ResponseEntity<>(this.laboratoryService.findAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Actualizar laboratorio",
            description = "Actualizar laboratorio para saber el laboratorio de un producto"
    )
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<LaboratoryDto> updateLaboratory(@PathVariable Long id, @RequestBody @Valid  LaboratoryDto laboratoryDto){
        return new ResponseEntity<>(this.laboratoryService.updateLaboratory(id, laboratoryDto),HttpStatus.OK );
    }
}
