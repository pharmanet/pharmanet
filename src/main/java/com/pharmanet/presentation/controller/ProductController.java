package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.presentation.dto.ProductDto;
import com.pharmanet.service.product.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Productos", description = "Productos")
public class ProductController {
    @Autowired
    private IProductService productService;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    public ResponseEntity<List<ProductDto>> findAll(){
        List<ProductDto> productosConDetallesYStock = productService.findAll();
        return ResponseEntity.ok(productosConDetallesYStock);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)  {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }
}
