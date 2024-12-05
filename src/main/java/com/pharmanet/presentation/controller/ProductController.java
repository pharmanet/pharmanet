package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.ProductDto;
import com.pharmanet.service.product.IProductService;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {

    @Autowired
    private IProductService productService;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add a new product", description = "Allows an ADMIN to add a new medicament.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'CAJERO')")
    @Operation(summary = "Get all products", description = "Permite a un ADMIN o CAJERO recuperar todos los productos con detalles y stock.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products fetched successfully",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)
    })
    public ResponseEntity<List<ProductDto>> findAll(){
        List<ProductDto> productosConDetallesYStock = productService.findAll();
        return ResponseEntity.ok(productosConDetallesYStock);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete a product", description = "Permite un administrador eliminar un producto por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)  {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update a product", description = "Permite a un administrador actualizar un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - Campos no validos")
    })
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }
}
