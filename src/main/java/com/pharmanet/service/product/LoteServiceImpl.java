package com.pharmanet.service.product;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Lote;
import com.pharmanet.persistence.entities.Product;
import com.pharmanet.persistence.repositories.*;
import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoteServiceImpl implements ILoteService{
    private final ModelMapper modelMapper;
    private  final IProductRepository productRepository;
    private final IProviderRepository providerRepository;
    private final ILoteRepository loteRepository;
    String url = "http://localhost:8080/";

    @Override
    public List<CatalogueDto> listCatalogs() {
        List<Lote> lotes = (List<Lote>) this.loteRepository.findAll();
        return lotes.stream().map(lote ->{
            CatalogueDto  catalogueDto = modelMapper.map(lote, CatalogueDto.class);
            catalogueDto.setImage(url + lote.getProduct().getImage());
            return catalogueDto;
        }).collect(Collectors.toList());
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
