package com.pharmanet.service.laboratory;


import com.pharmanet.presentation.dto.LaboratoryDto;

import java.util.List;

public interface ILaboratoryService {
   LaboratoryDto addLaboratory(LaboratoryDto laboratoryDto);
   String deleteLaboratory(Long id);
   List<LaboratoryDto> findAll();
   LaboratoryDto updateLaboratory(Long id, LaboratoryDto laboratoryDto);

}
