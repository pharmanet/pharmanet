package com.pharmanet.service.presentation;

import com.pharmanet.presentation.dto.PresentationDto;

import java.util.List;

public interface IPresentationService {
   PresentationDto addPresentation(PresentationDto presentationDto);
   String deletePresentation(Long id);
   List<PresentationDto> findAll();
   PresentationDto updatePrsentation(Long id, PresentationDto presentationDto);

}
