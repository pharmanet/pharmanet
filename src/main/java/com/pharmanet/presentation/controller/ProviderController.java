package com.pharmanet.presentation.controller;

import com.pharmanet.presentation.dto.ProviderDto;
import com.pharmanet.service.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/providers")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @PostMapping("/add")
    public ResponseEntity<ProviderDto> addProvider(@RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.addProvider(providerDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable Long id){
        return new ResponseEntity<>(providerService.deleteProvider(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProviderDto>> findAll(){
        return new ResponseEntity<>(providerService.findAll(), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable Long id, @RequestBody ProviderDto providerDto){
        return new ResponseEntity<>(providerService.updateProvider(id, providerDto), HttpStatus.OK);
    }
}
