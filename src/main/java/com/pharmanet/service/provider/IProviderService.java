package com.pharmanet.service.provider;

import com.pharmanet.presentation.dto.ProviderDto;

import java.util.List;

public interface IProviderService {
    ProviderDto addProvider(ProviderDto providerDto);
    String deleteProvider(Long id);
    List<ProviderDto> findAll();
    ProviderDto updateProvider(Long id, ProviderDto providerDto);

}
