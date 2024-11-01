package com.pharmanet.service.product;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.presentation.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto addProduct(ProductDto productDto);
    String deleteProduct(Long id);
    List<ProductDto> findAll();
    List<CatalogueDto> listCatalogs();
    ProductDto updateProduct(Long id, ProductDto productDto);
    LoteDto addLote(Long productId, LoteDto loteDto);
    String deleteLote(Long id);
}
