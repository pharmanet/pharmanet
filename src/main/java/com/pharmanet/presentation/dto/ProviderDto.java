package com.pharmanet.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
