package com.pharmanet.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    private Long id;
    @NotEmpty(message = "El nombre obligatorio")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;
    private String phone;
    @Email(message = "correo no válido")
    private String email;
    private String address;
}
