package com.pharmanet.service.product;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.presentation.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {
    ProductDto addProduct(ProductDto productDto);
    String deleteProduct(Long id);
    List<ProductDto> findAll();
    ProductDto updateProduct(Long id, ProductDto productDto);
}
