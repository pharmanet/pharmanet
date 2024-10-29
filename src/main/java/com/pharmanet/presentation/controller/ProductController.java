package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.presentation.dto.ProductDto;
import com.pharmanet.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAll(){
        List<ProductDto> productosConDetallesYStock = productService.findAll();
        return ResponseEntity.ok(productosConDetallesYStock);
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogueDto>> listCatalogs(){
        List<CatalogueDto> productos = productService.listCatalogs();
        return ResponseEntity.ok(productos);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productService.updateProduct(id, productDto), HttpStatus.OK);
    }

    @PostMapping("/{productId}/lotes")
    public ResponseEntity<LoteDto> addLote(@PathVariable Long productId, @RequestBody LoteDto loteDto){
        return new ResponseEntity<>(productService.addLote(productId, loteDto), HttpStatus.CREATED);
    }
}
