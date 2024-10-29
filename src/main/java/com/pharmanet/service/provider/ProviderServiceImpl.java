package com.pharmanet.service.provider;

import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Provider;
import com.pharmanet.persistence.repositories.IProviderRepository;
import com.pharmanet.presentation.dto.ProviderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements IProviderService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProviderRepository providerRepository;

    @Override
    public ProviderDto addProvider(ProviderDto providerDto) {
        Provider provider = modelMapper.map(providerDto, Provider.class);
        Provider providerSaved = providerRepository.save(provider);
        return modelMapper.map(providerSaved, ProviderDto.class);

    }

    @Override
    public String deleteProvider(Long id) {
       providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proveedor no existe"));
       providerRepository.deleteById(id);
       return "Eliminado con exito";
    }

    @Override
    public List<ProviderDto> findAll() {
        List<Provider> providers = (List<Provider>) providerRepository.findAll();
        return providers.stream().map(provider -> modelMapper.map(provider, ProviderDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public ProviderDto updateProvider(Long id, ProviderDto providerDto) {
        Provider provider = providerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proveedor no existe"));
        provider.setName(providerDto.getName());
        provider.setPhone(providerDto.getPhone());
        provider.setEmail(providerDto.getEmail());
        provider.setAddress(providerDto.getAddress());

        Provider providerUpdate = providerRepository.save(provider);
        return modelMapper.map(providerUpdate, ProviderDto.class);

    }
}
