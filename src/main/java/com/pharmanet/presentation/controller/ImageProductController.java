package com.pharmanet.presentation.controller;


import com.pharmanet.presentation.dto.ImageProductDto;
import com.pharmanet.presentation.dto.ProductDto;
import com.pharmanet.service.product.ImageProductService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Image", description = "Upload images")
public class ImageProductController {

    @Autowired
    private ImageProductService imageProductService;

    @PutMapping("/uploadImage/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Upload or update a product image",
            description = "Allows an ADMIN to upload or update an image for a specific product by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully",
                    content = @Content(schema = @Schema(implementation = ImageProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or file format",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - insufficient permissions",
                    content = @Content)
    })
    public ResponseEntity<ImageProductDto> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageProductService.update(id, file), HttpStatus.OK);
    }
}
