package com.pharmanet.presentation.dto;

import com.pharmanet.persistence.entities.Provider;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteDto {
    private Long id;
    private Provider provider;
    @NotNull(message = "Minimo un lote de stock")
    private Integer stock;
    private LocalDate expirationDate;
}
