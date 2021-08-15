package com.dictionary.Server.controllers;

import com.dictionary.Server.payload.request.AddDefinitionRequest;
import com.dictionary.Server.payload.request.FindTermRequest;
import com.dictionary.Server.payload.request.VoteRequest;
import com.dictionary.Server.services.DefinitionService;
import com.dictionary.Server.services.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/definition")
public class DefinitionControllers {

    private final DefinitionService definitionService;
    private final VoteService voteService;

    public DefinitionControllers(DefinitionService definitionService, VoteService voteService) {
        this.definitionService = definitionService;
        this.voteService = voteService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDefinition(@Valid @RequestBody AddDefinitionRequest addDefinitionRequest) {
        return new ResponseEntity<>(definitionService.addDefinition(addDefinitionRequest), HttpStatus.CREATED);
    }

    @PostMapping("/vote")
    public ResponseEntity<?> voteDefinition(@Valid @RequestBody VoteRequest voteRequest) {
        return new ResponseEntity<>(voteService.Voting(voteRequest), HttpStatus.OK);
    }

    /*
    @PostMapping("/findId")
    public ResponseEntity<?> findDefinition(@Valid @RequestBody Long id, @Valid @RequestBody String token) {
        return new ResponseEntity<>(definitionService.findDefinition(id, token), HttpStatus.OK);
    }*/

    @PostMapping("/findTerm")
    public ResponseEntity<?> findTerm(@Valid @RequestBody FindTermRequest findTermRequest) {
        return new ResponseEntity<>(definitionService.findTerm(findTermRequest), HttpStatus.OK);
    }
}
