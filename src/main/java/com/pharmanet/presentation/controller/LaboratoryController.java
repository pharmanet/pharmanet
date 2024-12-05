package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.LaboratoryDto;
import com.pharmanet.service.laboratory.ILaboratoryService;
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
@RequestMapping("/api/v1/laboratories")
@Tag(name = "Laboratorios", description = "API Gestion de presentacion para asignar el tipo de presentación a los medicamentos")
public class LaboratoryController {

    @Autowired
    private ILaboratoryService laboratoryService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new laboratory", description = "Permite a un administrador agregar un nuevo laboratorio para saber el tipo de laboratorio de un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = LaboratoryController.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<LaboratoryDto> addLaboratory(@RequestBody @Valid LaboratoryDto laboratoryDto) {
        return new ResponseEntity<>(laboratoryService.addLaboratory(laboratoryDto), HttpStatus.CREATED);

    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a laboratory", description = "Permite un administrador eliminar un laboratorio por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Laboratory not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deleteLaboratory(@PathVariable Long id){
        return new ResponseEntity<>(this.laboratoryService.deleteLaboratory(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get all laboratories", description = "Permite que un Administrador recupere los laboratorios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = LaboratoryDto.class))),
    })
    public ResponseEntity<List<LaboratoryDto>> findAll(){
        return new ResponseEntity<>(this.laboratoryService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update a laboratory", description = "Permite a un administrador actualizar a un laboratorio existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully",
                    content = @Content(schema = @Schema(implementation = LaboratoryDto.class))),
            @ApiResponse(responseCode = "404", description = "Laboratory not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<LaboratoryDto> updateLaboratory(@PathVariable Long id, @RequestBody @Valid  LaboratoryDto laboratoryDto){
        return new ResponseEntity<>(this.laboratoryService.updateLaboratory(id, laboratoryDto),HttpStatus.OK );
    }
}
