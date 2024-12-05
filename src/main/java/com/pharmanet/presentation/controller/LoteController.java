package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.CustomerDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.service.product.ILoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Lotes", description = "Gestion de lotes y catálogos")
public class LoteController {
    @Autowired
    private ILoteService loteService;

    @GetMapping("/catalogs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get all Catalogues", description = "Permite que un Administrador recupere a todos los catalogos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CatalogueDto.class))),
    })
    public ResponseEntity<List<CatalogueDto>> listCatalogs(){
        List<CatalogueDto> productos = loteService.listCatalogs();
        return ResponseEntity.ok(productos);
    }


    @PostMapping("/{productId}/lotes")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new lote", description = "Permite a un administrador agregar un nuevo lote.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = LoteDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<LoteDto> addLote(@PathVariable Long productId, @RequestBody LoteDto loteDto){
        return new ResponseEntity<>(loteService.addLote(productId, loteDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/lote/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a lote", description = "Permite un administrador eliminar un lote por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Lote not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deleteLote(@PathVariable Long id){
        return new ResponseEntity<>(loteService.deleteLote(id), HttpStatus.OK);
    }
}
