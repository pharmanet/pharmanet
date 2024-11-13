package com.pharmanet.presentation.dto;


import com.pharmanet.persistence.entities.Laboratory;
import com.pharmanet.persistence.entities.Presentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {
    private Long id;
    private String name;
    private String concentration;
    private String additional;
    private BigDecimal price;
    private Laboratory laboratory;
    private Presentation presentation;
    private Long totalStock;
    private String image;

}
