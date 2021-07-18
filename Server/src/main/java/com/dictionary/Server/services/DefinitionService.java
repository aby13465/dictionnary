package com.dictionary.Server.services;

import com.dictionary.Server.models.Definition;
import com.dictionary.Server.models.User;
import com.dictionary.Server.payload.request.AddDefinitionRequest;
import com.dictionary.Server.payload.response.DefinitionResponse;
import com.dictionary.Server.payload.response.MessageResponse;
import com.dictionary.Server.repository.DefinitionRepository;
import com.dictionary.Server.repository.UserRepository;
import com.dictionary.Server.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;
    private TokenUtil tokenUtil;
    private UserRepository userRepository;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository, TokenUtil tokenUtil, UserRepository userRepository) {
        this.definitionRepository = definitionRepository;
        this.tokenUtil = tokenUtil;
        this.userRepository = userRepository;
    }

    public Object addDefinition(AddDefinitionRequest addDefinitionRequest) {
        String token = addDefinitionRequest.getToken().substring("Bearer ".length());
        String username = tokenUtil.getUserNameFromToken(token);
        if (username != null) {
            User user = userRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found."));
            if (tokenUtil.isTokenValid(token, user)) {
                Definition definition = new Definition();
                definition.setAuthor(user);
                definition.setTerm(addDefinitionRequest.getTerm());
                definition.setDefinition(addDefinitionRequest.getDefinition());
                definition.setExamples(addDefinitionRequest.getExamples());
                definition.setCreationDate(LocalDate.now());
                Definition newDefinition = this.definitionRepository.save(definition);
                DefinitionResponse definitionResponse =
                        new DefinitionResponse(
                                newDefinition.getId(),
                                newDefinition.getTerm(),
                                newDefinition.getDefinition(),
                                newDefinition.getExamples(),
                                newDefinition.getCreationDate(),
                                newDefinition.getAuthor().getUsername());
                return definitionResponse;
            }
            return new MessageResponse("Token not valid.");
        }
        return new MessageResponse("User Doesn't exist.");
    }

    public Object findDefinition(Long id) {
        Definition definition = definitionRepository.findDefinitionById(id);
        if (definition == null)
            return new MessageResponse("Defintion doesn't exist");
        return new DefinitionResponse(
                definition.getId(),
                definition.getTerm(),
                definition.getDefinition(),
                definition.getExamples(),
                definition.getCreationDate(),
                definition.getAuthor().getUsername());
    }

    public Object findTerm(String term) {
        List<Definition> definitions = definitionRepository.findDefinitionByTerm(term);
        List<DefinitionResponse> definitionResponses = new ArrayList<>();

        for (Definition definition : definitions) {
            DefinitionResponse definitionResponse =
                    new DefinitionResponse(
                            definition.getId(),
                            definition.getTerm(),
                            definition.getDefinition(),
                            definition.getExamples(),
                            definition.getCreationDate(),
                            definition.getAuthor().getUsername());
            definitionResponses.add(definitionResponse);
        }

        return definitionResponses;
    }
}
