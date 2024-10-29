package com.pharmanet.service.laboratory;

import com.pharmanet.exception.AlreadyExistsException;
import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Laboratory;
import com.pharmanet.persistence.repositories.ILaboratoryRepository;
import com.pharmanet.presentation.dto.LaboratoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaboratoryServiceImpl implements ILaboratoryService {
    @Autowired
    private ILaboratoryRepository laboratoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LaboratoryDto addLaboratory(LaboratoryDto laboratoryDto) {
        Optional<Laboratory> existingLaboratory = laboratoryRepository.findByName(laboratoryDto.getName());

        if(existingLaboratory.isPresent()){
            throw new AlreadyExistsException("Ya existe un laboratorio con el mismo nombre");
        }
        Laboratory laboratory = modelMapper.map(laboratoryDto, Laboratory.class);
        Laboratory laboratorySaved = laboratoryRepository.save(laboratory);
        return modelMapper.map(laboratorySaved, LaboratoryDto.class);

    }

    @Override
    public String deleteLaboratory(Long id) {
        laboratoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));
        laboratoryRepository.deleteById(id);
        return "Eliminado con exito";
    }

    @Override
    public List<LaboratoryDto> findAll() {
        List<Laboratory> laboratories = (List<Laboratory>) laboratoryRepository.findAll();
        return laboratories.stream().map(laboratory -> modelMapper.map(laboratory, LaboratoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LaboratoryDto updateLaboratory(Long id, LaboratoryDto laboratoryDto) {
        Laboratory laboratory = laboratoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Laboratorio no existe"));

        Optional<Laboratory> findLaoboratory = laboratoryRepository.findByName(laboratoryDto.getName());
        if(findLaoboratory.isPresent() && !findLaoboratory.get().equals(laboratory)){
            throw new ResourceNotFoundException("Ya existe un laboratorio con el mismo nombre");
        }

        laboratory.setName(laboratoryDto.getName());
        Laboratory laboratoryUpdate = laboratoryRepository.save(laboratory);
        return modelMapper.map(laboratoryUpdate, LaboratoryDto.class);

    }
}
