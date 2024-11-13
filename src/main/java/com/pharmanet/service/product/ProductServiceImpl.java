package com.pharmanet.service.product;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Lote;
import com.pharmanet.persistence.entities.Product;
import com.pharmanet.persistence.repositories.*;
import com.pharmanet.presentation.dto.ProductDto;
import com.pharmanet.service.upload.UploadService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ModelMapper modelMapper;
    private  final IProductRepository productRepository;
    private final ILaboratoryRepository laboratoryRepository;
    private final IPresentationRepository presentationRepository;
    private final UploadService uploadService;
    String url = "http://localhost:8080/";

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        if(productDto.getLaboratory().getId() == null){
            throw new ResourceNotFoundException("Campo obligatorio");
        }

        if(productDto.getPresentation().getId() == null){
            throw new ResourceNotFoundException("Campo obligatorio");
        }

        return laboratoryRepository.findById(product.getLaboratory().getId()).map(laboratory -> {
            product.setLaboratory(laboratory);

            return presentationRepository.findById(product.getPresentation().getId()).map(presentation -> {
                product.setPresentation(presentation);
                product.setImage("default.jpg");
                Product productSaved = productRepository.save(product);
                return modelMapper.map(productSaved, ProductDto.class);
            }).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));
        }).orElseThrow(() -> new ResourceNotFoundException("Laboratorio no existe"));
    }

    @Override
    public String deleteProduct(Long id)  {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe producto para eliminar"));
        if(!product.getImage().equals("default.jpg")){
            uploadService.deleteUpload(product.getImage());
        }
        productRepository.deleteById(id);
        return "Eliminado con exito";
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = (List<Product>) productRepository.findAll();

        return products.stream().map(product -> {
            ProductDto productDto = modelMapper.map(product, ProductDto.class);

            Long totalStock = product.getLotes().stream()
                    .mapToLong(Lote::getStock)
                    .sum();

            productDto.setTotalStock(totalStock);  // Asignar el totalStock al DTO
            productDto.setImage(url + product.getImage());
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe producto para actualizar"));
        if(productDto.getLaboratory().getId() == null){
            throw new ResourceNotFoundException("Campo obligatorio: laboratorio");
        }
        if(productDto.getPresentation().getId() == null){
            throw new ResourceNotFoundException("Campo obligatorio: presentación");
        }
        return laboratoryRepository.findById(productDto.getLaboratory().getId())
                .map(laboratory -> {
                    existingProduct.setName(productDto.getName());
                    existingProduct.setConcentration(productDto.getConcentration());
                    existingProduct.setAdditional(productDto.getAdditional());
                    existingProduct.setPrice(productDto.getPrice());
                    existingProduct.setLaboratory(laboratory);

                    return presentationRepository.findById(productDto.getPresentation().getId())
                            .map(presentation -> {
                                existingProduct.setPresentation(presentation);
                                Product productSaved = productRepository.save(existingProduct);

                                return modelMapper.map(productSaved, ProductDto.class);
                            }).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));
                }).orElseThrow(() -> new ResourceNotFoundException("Laboratorio no existe"));

    }
}
