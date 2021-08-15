package com.dictionary.Server.services;

import com.dictionary.Server.models.*;
import com.dictionary.Server.payload.request.AddDefinitionRequest;
import com.dictionary.Server.payload.request.FindTermRequest;
import com.dictionary.Server.payload.response.DefinitionResponse;
import com.dictionary.Server.payload.response.MessageResponse;
import com.dictionary.Server.repository.DefinitionRepository;
import com.dictionary.Server.repository.UserRepository;
import com.dictionary.Server.repository.VoteRepository;
import com.dictionary.Server.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefinitionService {

    private final DefinitionRepository definitionRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private TokenUtil tokenUtil;

    @Autowired
    public DefinitionService(DefinitionRepository definitionRepository, TokenUtil tokenUtil, UserRepository userRepository, VoteRepository voteRepository) {
        this.definitionRepository = definitionRepository;
        this.tokenUtil = tokenUtil;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
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
                this.definitionRepository.save(definition);
                return new MessageResponse("Success");
            }
            return new MessageResponse("Token not valid.");
        }
        return new MessageResponse("User Doesn't exist.");
    }
    /*
    public Object findDefinition(Long id, String token) {
        String username = null;
        if (token != "") {
            token = token.substring("Bearer ".length());
            username = tokenUtil.getUserNameFromToken(token);
        }
        Definition definition = definitionRepository.findDefinitionById(id).orElseThrow(() -> new RuntimeException("Definition not found."));
        int likes = 0;
        int dislikes = 0;
        VoteState voteState;
        if (username != null) {
            Vote vote = this.voteRepository.findVoteByVoteId(new VoteId(username,definition.getId()));
            if(vote == null)
                voteState = VoteState.none;
            else if(vote.isVote())
                voteState = VoteState.liked;
            else
                voteState = VoteState.disliked;
        } else
            voteState = VoteState.none;

        for (Vote vote : definition.getVotes()) {
            if (vote.isVote()) {
                likes += 1;
            } else {
                dislikes += 1;
            }
        }
        if (definition == null)
            return new MessageResponse("Defintion doesn't exist");
        return new DefinitionResponse(
                definition.getId(),
                definition.getTerm(),
                definition.getDefinition(),
                definition.getExamples(),
                definition.getCreationDate(),
                definition.getAuthor().getUsername(),
                likes,
                dislikes,
                voteState.toString()
        );
    }
    */
    public Object findTerm(FindTermRequest findTermRequest) {
        String username = null;
        if (findTermRequest.getToken() != "")
            username = tokenUtil.getUserNameFromToken(findTermRequest.getToken().substring("Bearer ".length()));

        List<Definition> definitions = definitionRepository.findDefinitionByTerm(findTermRequest.getTerm());
        List<DefinitionResponse> definitionResponses = new ArrayList<>();

        for (Definition definition : definitions) {
            int likes = 0;
            int dislikes = 0;
            VoteState voteState;

            if (username != null) {
                Vote vote = this.voteRepository.findVoteByVoteId(new VoteId(username,definition.getId()));
                if(vote == null)
                    voteState = VoteState.none;
                else if(vote.isVote())
                    voteState = VoteState.liked;
                else
                    voteState = VoteState.disliked;
            } else
                voteState = VoteState.none;

            for (Vote vote : definition.getVotes()) {
                if (vote.isVote()) {
                    likes += 1;
                } else {
                    dislikes += 1;
                }
            }
            DefinitionResponse definitionResponse =
                    new DefinitionResponse(
                            definition.getId(),
                            definition.getTerm(),
                            definition.getDefinition(),
                            definition.getExamples(),
                            definition.getCreationDate(),
                            definition.getAuthor().getUsername(),
                            likes,
                            dislikes,
                            voteState.toString()
                    );
            definitionResponses.add(definitionResponse);
        }

        return definitionResponses;
    }
}
