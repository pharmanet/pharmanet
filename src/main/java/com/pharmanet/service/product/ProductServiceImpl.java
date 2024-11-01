package com.pharmanet.service.product;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Lote;
import com.pharmanet.persistence.entities.Product;
import com.pharmanet.persistence.repositories.*;
import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import com.pharmanet.presentation.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ILaboratoryRepository laboratoryRepository;
    @Autowired
    private IPresentationRepository presentationRepository;
    @Autowired
    private IProviderRepository providerRepository;
    @Autowired
    private ILoteRepository loteRepository;


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

                Product productSaved = productRepository.save(product);
                return modelMapper.map(productSaved, ProductDto.class);
            }).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));
        }).orElseThrow(() -> new ResourceNotFoundException("Laboratorio no existe"));
    }

    @Override
    public String deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe producto para eliminar"));
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
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CatalogueDto> listCatalogs() {
        List<Lote> lotes = (List<Lote>) this.loteRepository.findAll();
        return lotes.stream().map(lote -> modelMapper.map(lote, CatalogueDto.class))
                .collect(Collectors.toList());
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

    @Override
    public LoteDto addLote(Long productId, LoteDto loteDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Lote lote = modelMapper.map(loteDto, Lote.class);

        if(loteDto.getProvider().getId() == null){
            throw new ResourceNotFoundException("Campo obligatorio: provider");
        }

        return providerRepository.findById(lote.getProvider().getId()).map(provider -> {
            lote.setProvider(provider);
            lote.setProduct(existingProduct);
            Lote loteSaved = loteRepository.save(lote);
            return modelMapper.map(loteSaved, LoteDto.class);
        }).orElseThrow(() -> new ResourceNotFoundException("Provider no existe"));

    }

    @Override
    public String deleteLote(Long id) {
        loteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe lote para eliminar"));
        loteRepository.deleteById(id);
        return "Eliminado con exito";
    }
}
