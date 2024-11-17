package com.pharmanet.presentation.dto;


import com.pharmanet.persistence.entities.Laboratory;
import com.pharmanet.persistence.entities.Presentation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "nombre del producto obligatorio")
    private String name;
    private String concentration;
    private String additional;
    @NotNull(message = "campo precio obligatorio")
    private BigDecimal price;
    private Laboratory laboratory;
    private Presentation presentation;
    private Long totalStock;
    private String image;

}
