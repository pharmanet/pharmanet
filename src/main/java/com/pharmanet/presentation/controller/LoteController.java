package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.service.product.ILoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Lotes", description = "Lotes y Catálogos")
public class LoteController {
    @Autowired
    private ILoteService loteService;

    @GetMapping("/catalogs")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<CatalogueDto>> listCatalogs(){
        List<CatalogueDto> productos = loteService.listCatalogs();
        return ResponseEntity.ok(productos);
    }
    @PostMapping("/{productId}/lotes")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<LoteDto> addLote(@PathVariable Long productId, @RequestBody LoteDto loteDto){
        return new ResponseEntity<>(loteService.addLote(productId, loteDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/lote/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteLote(@PathVariable Long id){
        return new ResponseEntity<>(loteService.deleteLote(id), HttpStatus.OK);
    }
}
