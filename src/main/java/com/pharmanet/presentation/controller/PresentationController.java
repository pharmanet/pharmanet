package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.PresentationDto;
import com.pharmanet.service.presentation.IPresentationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/presentations")
public class PresentationController {
    @Autowired
    private IPresentationService presentationService;

    @PostMapping("/add")
    public ResponseEntity<PresentationDto> addPresentation(@RequestBody @Valid PresentationDto presentationDto) {
        return new ResponseEntity<>(presentationService.addPresentation(presentationDto), HttpStatus.CREATED);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePresentation(@PathVariable Long id){
        return new ResponseEntity<>(this.presentationService.deletePresentation(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PresentationDto>> findAll(){
        return new ResponseEntity<>(this.presentationService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PresentationDto> updatePresentation(@PathVariable Long id, @RequestBody @Valid PresentationDto presentationDto){
        return new ResponseEntity<>(this.presentationService.updatePrsentation(id, presentationDto),HttpStatus.OK );
    }
}
