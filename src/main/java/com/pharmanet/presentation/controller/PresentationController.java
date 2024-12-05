package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.PresentationDto;
import com.pharmanet.service.presentation.IPresentationService;
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
@RequestMapping("/api/v1/presentations")
@Tag(name = "Presentaciones", description = "API Gestion de presentacion para asignar el tipo de presentación a los medicamentos")
public class PresentationController {

    @Autowired
    private IPresentationService presentationService;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new proveedor", description = "Permite a un administrador agregar un nuevo presentación para saber el tipo de presentación de un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = PresentationDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<PresentationDto> addPresentation(@RequestBody @Valid PresentationDto presentationDto) {
        return new ResponseEntity<>(presentationService.addPresentation(presentationDto), HttpStatus.CREATED);

    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a presentation", description = "Permite un administrador eliminar una presentación por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Presentation not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deletePresentation(@PathVariable Long id){
        return new ResponseEntity<>(this.presentationService.deletePresentation(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get all presentations", description = "Permite que un Administrador recupere las presentaciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PresentationDto.class))),
    })
    public ResponseEntity<List<PresentationDto>> findAll(){
        return new ResponseEntity<>(this.presentationService.findAll(), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update a presentation", description = "Permite a un administrador actualizar a una presentación existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully",
                    content = @Content(schema = @Schema(implementation = PresentationDto.class))),
            @ApiResponse(responseCode = "404", description = "Presentation not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<PresentationDto> updatePresentation(@PathVariable Long id, @RequestBody @Valid PresentationDto presentationDto){
        return new ResponseEntity<>(this.presentationService.updatePrsentation(id, presentationDto),HttpStatus.OK );
    }
}
