package com.dictionary.Server.controllers;

import com.dictionary.Server.payload.request.AddDefinitionRequest;
import com.dictionary.Server.services.DefinitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/definition")
public class DefinitionControllers {

    private final DefinitionService definitionService;

    public DefinitionControllers(DefinitionService definitionService) {
        this.definitionService = definitionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDefinition(@Valid @RequestBody AddDefinitionRequest addDefinitionRequest) {
        return new ResponseEntity<>(definitionService.addDefinition(addDefinitionRequest), HttpStatus.CREATED);
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<?> findDefinition(@PathVariable Long id){
        return new ResponseEntity<>(definitionService.findDefinition(id) ,HttpStatus.OK);
    }

    @GetMapping("/findTerm/{term}")
    public ResponseEntity<?> findTerm(@PathVariable String term){
        return new ResponseEntity<>(definitionService.findTerm(term) ,HttpStatus.OK);
    }
}
