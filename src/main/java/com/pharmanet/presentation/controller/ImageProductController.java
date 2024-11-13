package com.pharmanet.presentation.controller;


import com.pharmanet.presentation.dto.ImageProductDto;
import com.pharmanet.service.product.ImageProductService;
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
@Tag(name = "Image", description = "Subida de imagenes")
public class ImageProductController {

    @Autowired
    private ImageProductService imageProductService;

    @PutMapping("/uploadImage/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ImageProductDto> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageProductService.update(id, file), HttpStatus.OK);
    }
}
