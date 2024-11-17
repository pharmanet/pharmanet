package com.pharmanet.presentation.dto;

import com.pharmanet.config.validations.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {
    private Long id;
    @NotEmpty(message = "El nombre obligatorio")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String name;
    @NotEmpty(message = "correo obligatorio")
    @Email(message = "correo no válido")
    private String email;
    @PhoneNumber
    private String phone;
    private String dni;

}
