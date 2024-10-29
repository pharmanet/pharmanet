package com.pharmanet.service.presentation;

import com.pharmanet.exception.AlreadyExistsException;
import com.pharmanet.exception.ResourceNotFoundException;
import com.pharmanet.persistence.entities.Presentation;
import com.pharmanet.persistence.repositories.IPresentationRepository;
import com.pharmanet.presentation.dto.PresentationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresentationServiceImpl implements IPresentationService {
    @Autowired
    private IPresentationRepository presentationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PresentationDto addPresentation(PresentationDto presentationDto) {
        Optional<Presentation> existingPresentation = presentationRepository.findByName(presentationDto.getName());
        if(existingPresentation.isPresent()){
            throw new AlreadyExistsException("Ya existe una presentación con el mismo nombre");
        }
        Presentation presentation = modelMapper.map(presentationDto, Presentation.class);
        Presentation presentationSaved = presentationRepository.save(presentation);
        return modelMapper.map(presentationSaved, PresentationDto.class);

    }

    @Override
    public String deletePresentation(Long id) {
        presentationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));
        presentationRepository.deleteById(id);
        return "Eliminado con exito";

    }

    @Override
    public List<PresentationDto> findAll() {
        List<Presentation> presentations = (List<Presentation>) presentationRepository.findAll();
        return presentations.stream().map(presentation -> modelMapper.map(presentation, PresentationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PresentationDto updatePrsentation(Long id, PresentationDto presentationDto) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Presentación no existe"));

        Optional<Presentation> findPresentation = presentationRepository.findByName(presentationDto.getName());
        if(findPresentation.isPresent() && !findPresentation.get().equals(presentation)){
            throw new ResourceNotFoundException("Ya existe un presentación con el mismo nombre");
        }

        presentation.setName(presentationDto.getName());
        Presentation presentationUpdate = presentationRepository.save(presentation);
        return modelMapper.map(presentationUpdate, PresentationDto.class);

    }
}
