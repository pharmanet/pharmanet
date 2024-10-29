package com.pharmanet.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaboratoryDto {
    private Long id;
    @NotBlank(message = "No puede ser nulo")
    private String name;
}
