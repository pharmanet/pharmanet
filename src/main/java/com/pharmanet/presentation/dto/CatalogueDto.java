package com.pharmanet.presentation.dto;

import com.pharmanet.persistence.entities.Product;
import com.pharmanet.persistence.entities.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueDto {
    private Long id;
    private Provider provider;
    private Product product;
    private Integer stock;
    private LocalDate expirationDate;
    private String image;
}
