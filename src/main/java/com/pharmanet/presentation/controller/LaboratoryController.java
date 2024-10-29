package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.LaboratoryDto;
import com.pharmanet.service.laboratory.ILaboratoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laboratories")
public class LaboratoryController {
    @Autowired
    private ILaboratoryService laboratoryService;

    @PostMapping("/add")
    public ResponseEntity<LaboratoryDto> addLaboratory(@RequestBody @Valid LaboratoryDto laboratoryDto) {
        return new ResponseEntity<>(laboratoryService.addLaboratory(laboratoryDto), HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLaboratory(@PathVariable Long id){
        return new ResponseEntity<>(this.laboratoryService.deleteLaboratory(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<LaboratoryDto>> findAll(){
        return new ResponseEntity<>(this.laboratoryService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LaboratoryDto> updateLaboratory(@PathVariable Long id, @RequestBody @Valid  LaboratoryDto laboratoryDto){
        return new ResponseEntity<>(this.laboratoryService.updateLaboratory(id, laboratoryDto),HttpStatus.OK );
    }
}
