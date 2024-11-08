package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.PresentationDto;
import com.pharmanet.service.presentation.IPresentationService;
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
@RequestMapping("/api/v1/presentations")
@Tag(name = "Presentaciones", description = "Presentaciones para asignar a los medicamentos")
public class PresentationController {

    @Autowired
    private IPresentationService presentationService;

    @Operation(
            summary = "Registrar presentación",
            description = "Registrar presentación para saber el tipo de presentación de un medicamento"
    )
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PresentationDto> addPresentation(@RequestBody @Valid PresentationDto presentationDto) {
        return new ResponseEntity<>(presentationService.addPresentation(presentationDto), HttpStatus.CREATED);

    }
    @Operation(
            summary = "Eliminar  presentación",
            description = "Eliminar presentación por id"
    )
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deletePresentation(@PathVariable Long id){
        return new ResponseEntity<>(this.presentationService.deletePresentation(id), HttpStatus.OK);
    }
    @Operation(
            summary = "Listar presentaciones",
            description = "Listar todas las presentaciones"
    )
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<PresentationDto>> findAll(){
        return new ResponseEntity<>(this.presentationService.findAll(), HttpStatus.OK);
    }
    @Operation(
            summary = "Actualizar presentación",
            description = "Actualizar una presentación por id"
    )
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PresentationDto> updatePresentation(@PathVariable Long id, @RequestBody @Valid PresentationDto presentationDto){
        return new ResponseEntity<>(this.presentationService.updatePrsentation(id, presentationDto),HttpStatus.OK );
    }
}
