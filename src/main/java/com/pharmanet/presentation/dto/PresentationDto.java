package com.pharmanet.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PresentationDto {
    private Long id;
    @NotBlank(message = "No puede ser nulo")
    private String name;


}
