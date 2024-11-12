package com.pharmanet.service.product;

import com.pharmanet.presentation.dto.CatalogueDto;
import com.pharmanet.presentation.dto.LoteDto;

import java.util.List;

public interface ILoteService {
    List<CatalogueDto> listCatalogs();
    LoteDto addLote(Long productId, LoteDto loteDto);
    String deleteLote(Long id);

}
