package com.pharmanet.service.product;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Product;
import com.pharmanet.persistence.repositories.IProductRepository;
import com.pharmanet.presentation.dto.ImageProductDto;
import com.pharmanet.service.upload.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageProductService {
    private final IProductRepository productRepository;
    private final UploadService uploadService;

    @Override
    public ImageProductDto update(Long id, MultipartFile file) throws IOException {
        String nombre = uploadService.saveUpload(file);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe producto"));

        product.setImage(nombre);
        productRepository.save(product);
        ImageProductDto imageProductDto = new ImageProductDto();
        imageProductDto.setImage(product.getImage());
        return imageProductDto;
    }
}
